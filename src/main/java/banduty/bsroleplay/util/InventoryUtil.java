package banduty.bsroleplay.util;

import banduty.bsroleplay.BsRolePlay;
import com.google.gson.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.MinecraftServer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InventoryUtil {
    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(NbtElement.class, new NbtElementTypeAdapter())
            .setPrettyPrinting()
            .create();

    public static void saveInventoryToFile(PlayerEntity player) {
        File file = getInventoryFile(player);
        MinecraftServer server = player.getServer();
        if (server == null) {
            BsRolePlay.LOGGER.error("Player is not in a server context. Cannot save inventory.");
            return;
        }

        List<NbtCompound> inventoryNbt = new ArrayList<>();
        for (int i = 0; i < player.getInventory().size(); i++) {
            ItemStack stack = player.getInventory().getStack(i);
            RegistryWrapper.WrapperLookup registries = server.getRegistryManager();
            if (!stack.isEmpty()) {
                Optional<NbtElement> nbtOptional = ItemStack.CODEC.encodeStart(registries.getOps(NbtOps.INSTANCE), stack).result();
                if (nbtOptional.isPresent() && nbtOptional.get() instanceof NbtCompound) {
                    inventoryNbt.add((NbtCompound) nbtOptional.get());
                } else {
                    BsRolePlay.LOGGER.warn("Failed to serialize ItemStack at slot {} for player {}", i, player.getName().getString());
                    inventoryNbt.add(new NbtCompound());
                }
                player.getInventory().setStack(i, ItemStack.EMPTY);
            } else {
                inventoryNbt.add(new NbtCompound());
            }
        }

        try (FileWriter writer = new FileWriter(file)) {
            GSON.toJson(inventoryNbt, writer);

            BsRolePlay.LOGGER.info("Inventory saved and cleared for player: {}", player.getName().getString());
        } catch (IOException e) {
            BsRolePlay.LOGGER.error("Failed to save inventory to file: {}", file.getAbsolutePath(), e);
        }
    }

    public static void loadInventoryFromFile(PlayerEntity player) {
        File file = getInventoryFile(player);
        MinecraftServer server = player.getServer();
        if (server == null) {
            BsRolePlay.LOGGER.error("Player is not in a server context. Cannot load inventory.");
            return;
        }

        RegistryWrapper.WrapperLookup registries = server.getRegistryManager();

        try (FileReader reader = new FileReader(file)) {
            List<NbtCompound> inventoryNbt = GSON.fromJson(reader, new com.google.common.reflect.TypeToken<List<NbtCompound>>() {}.getType());

            for (int i = 0; i < inventoryNbt.size(); i++) {
                NbtCompound nbt = inventoryNbt.get(i);
                if (nbt != null && !nbt.isEmpty()) {
                    try {
                        Optional<ItemStack> stackOptional = ItemStack.fromNbt(registries, nbt);
                        if (stackOptional.isPresent()) {
                            player.getInventory().setStack(i, stackOptional.get());
                        } else {
                            BsRolePlay.LOGGER.warn("Failed to deserialize ItemStack at slot {} for player {}", i, player.getName().getString());
                            BsRolePlay.LOGGER.warn("NBT Data: {}", nbt);
                            player.getInventory().setStack(i, ItemStack.EMPTY);
                        }
                    } catch (Exception e) {
                        BsRolePlay.LOGGER.error("Error deserializing ItemStack at slot {} for player {}", i, player.getName().getString(), e);
                        BsRolePlay.LOGGER.error("NBT Data: {}", nbt);
                        player.getInventory().setStack(i, ItemStack.EMPTY);
                    }
                } else {
                    player.getInventory().setStack(i, ItemStack.EMPTY);
                }
            }
        } catch (IOException e) {
            BsRolePlay.LOGGER.error("Failed to load inventory from file: {}", file.getAbsolutePath(), e);
        }
    }

    private static File getInventoryFile(PlayerEntity player) {
        MinecraftServer server = player.getServer();
        if (server == null) {
            BsRolePlay.LOGGER.error("Player is not in a server context. Cannot determine world directory.");
            throw new IllegalStateException("Player is not in a server context.");
        }

        String worldName = server.getSaveProperties().getLevelName();
        File baseDir = new File("saves/" + worldName + "/bsroleplay/inventorysaved/");

        if (!baseDir.exists()) {
            boolean dirsCreated = baseDir.mkdirs();
            if (!dirsCreated) {
                BsRolePlay.LOGGER.error("Failed to create directory: {}", baseDir.getAbsolutePath());
                throw new IllegalStateException("Failed to create directory: " + baseDir.getAbsolutePath());
            }
        }

        return new File(baseDir, player.getUuidAsString() + ".json");
    }

    private static class NbtElementTypeAdapter implements JsonSerializer<NbtElement>, JsonDeserializer<NbtElement> {
        @Override
        public JsonElement serialize(NbtElement src, Type typeOfSrc, JsonSerializationContext context) {
            if (src instanceof NbtCompound) {
                return serializeNbtCompound((NbtCompound) src, context);
            } else if (src instanceof NbtList) {
                return serializeNbtList((NbtList) src, context);
            } else if (src instanceof NbtString) {
                return new JsonPrimitive(src.asString());
            } else if (src instanceof AbstractNbtNumber) {
                return new JsonPrimitive(((AbstractNbtNumber) src).numberValue());
            } else if (src instanceof NbtByte) {
                return new JsonPrimitive(((NbtByte) src).byteValue() != 0);
            }
            throw new JsonParseException("Unsupported NBT type: " + src.getClass());
        }

        private JsonElement serializeNbtCompound(NbtCompound compound, JsonSerializationContext context) {
            JsonObject json = new JsonObject();
            for (String key : compound.getKeys()) {
                json.add(key, context.serialize(compound.get(key)));
            }
            return json;
        }

        private JsonElement serializeNbtList(NbtList list, JsonSerializationContext context) {
            JsonArray json = new JsonArray();
            for (NbtElement element : list) {
                json.add(context.serialize(element));
            }
            return json;
        }

        @Override
        public NbtElement deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if (json.isJsonObject()) {
                NbtCompound compound = new NbtCompound();
                JsonObject obj = json.getAsJsonObject();
                for (String key : obj.keySet()) {
                    compound.put(key, context.deserialize(obj.get(key), NbtElement.class));
                }
                return compound;
            } else if (json.isJsonArray()) {
                NbtList list = new NbtList();
                JsonArray arr = json.getAsJsonArray();
                for (JsonElement element : arr) {
                    list.add(context.deserialize(element, NbtElement.class));
                }
                return list;
            } else if (json.isJsonPrimitive()) {
                JsonPrimitive primitive = json.getAsJsonPrimitive();
                if (primitive.isString()) {
                    return NbtString.of(primitive.getAsString());
                } else if (primitive.isNumber()) {
                    return NbtDouble.of(primitive.getAsDouble());
                } else if (primitive.isBoolean()) {
                    return NbtByte.of(primitive.getAsBoolean() ? (byte) 1 : (byte) 0);
                }
            }
            throw new JsonParseException("Cannot deserialize NBT from: " + json);
        }
    }
}