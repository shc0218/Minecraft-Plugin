package org.shc0218.minecraft.User;

import com.google.gson.Gson;
import okhttp3.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.shc0218.minecraft.Gogo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Update {
    private static Map<String, BukkitTask> UserUpdates = new HashMap<String, BukkitTask>();
    public void SetUserDataUpdate(Player player) {
        if (!UserUpdates.containsKey(player.getName())) {
            BukkitTask UserDataUpdateRunnable = new BukkitRunnable() {
                public void run() {
                    String Url = Gogo.getPlugin(Gogo.class).getConfig().getString("RestApiUrl") + "api/user/update";
                    Gson gson = new Gson();
                    Map<String, Object> postBodyMap = new HashMap<>();
                    postBodyMap.put("UUID", player.getUniqueId().toString());
                    postBodyMap.put("UserId", player.getName());
                    postBodyMap.put("UserHealth", player.getHealth());
                    postBodyMap.put("UserLevel", player.getExpToLevel());
                    postBodyMap.put("UserHungryLevel", player.getFoodLevel());
                    postBodyMap.put("UserLocation",
                            new String[]{
                                    Integer.toString((int) player.getLocation().getBlockX()),
                                    Integer.toString((int) player.getLocation().getBlockY()),
                                    Integer.toString((int) player.getLocation().getBlockZ())
                            });
                    String json = gson.toJson(postBodyMap);
                    Request request = new Request.Builder()
                            .url(Url)
                            .post(RequestBody.create(MediaType.get("application/json"), json))
                            .build();
                    OkHttpClient client = new OkHttpClient();

                    try {
                        Response response = client.newCall(request).execute();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.runTaskTimer(Gogo.getPlugin(Gogo.class), 0L, 20L);
            UserUpdates.put(player.getUniqueId().toString(), UserDataUpdateRunnable);
        }
    }
    public void DeleteUserDataUpdate(Player player) {
        if (UserUpdates.containsKey(player.getUniqueId().toString())) {
            UserUpdates.get(player.getUniqueId().toString()).cancel();
            UserUpdates.remove(player.getUniqueId().toString());
        }
    }

    public void PlayerJoinRequest(Player player) {
        String Url = Gogo.getPlugin(Gogo.class).getConfig().getString("RestApiUrl") + "api/user/set";
        Gson gson = new Gson();
        Map<String, Object> postBodyMap = new HashMap<>();
        postBodyMap.put("UUID", player.getUniqueId().toString());
        postBodyMap.put("UserId", player.getName());
        postBodyMap.put("UserHealth", player.getHealth());
        postBodyMap.put("UserLevel", player.getExpToLevel());
        postBodyMap.put("UserHungryLevel", player.getFoodLevel());
        postBodyMap.put("UserLocation",
                new String[]{
                        Integer.toString((int) player.getLocation().getBlockX()),
                        Integer.toString((int) player.getLocation().getBlockY()),
                        Integer.toString((int) player.getLocation().getBlockZ())
                });
        String json = gson.toJson(postBodyMap);
        Request request = new Request.Builder()
                .url(Url)
                .post(RequestBody.create(MediaType.get("application/json"), json))
                .build();
        OkHttpClient client = new OkHttpClient();

        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void PlayerQuitRequest(Player player) {
        String Url = Gogo.getPlugin(Gogo.class).getConfig().getString("RestApiUrl") + "api/user/delete";
        Gson gson = new Gson();
        Map<String, Object> postBodyMap = new HashMap<>();
        postBodyMap.put("UUID", player.getUniqueId().toString());
        String json = gson.toJson(postBodyMap);
        Request request = new Request.Builder()
                .url(Url)
                .post(RequestBody.create(MediaType.get("application/json"), json))
                .build();
        OkHttpClient client = new OkHttpClient();

        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
