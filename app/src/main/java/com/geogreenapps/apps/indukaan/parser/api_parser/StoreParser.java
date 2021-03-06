package com.geogreenapps.apps.indukaan.parser.api_parser;


import android.util.Log;

import com.geogreenapps.apps.indukaan.appconfig.AppContext;
import com.geogreenapps.apps.indukaan.classes.Images;
import com.geogreenapps.apps.indukaan.classes.Store;
import com.geogreenapps.apps.indukaan.classes.User;
import com.geogreenapps.apps.indukaan.parser.Parser;
import com.geogreenapps.apps.indukaan.parser.tags.Tags;

import org.json.JSONException;
import org.json.JSONObject;

import io.realm.RealmList;


public class StoreParser extends Parser {

    public StoreParser(JSONObject json) {
        super(json);
    }

    public RealmList<Store> getStore() {

        RealmList<Store> list = new RealmList<Store>();

        try {

            JSONObject json_array = json.getJSONObject(Tags.RESULT);

            for (int i = 0; i < json_array.length(); i++) {


                try {
                    JSONObject json_user = json_array.getJSONObject(i + "");
                    Store store = new Store();
                    store.setId(json_user.getInt("id_store"));
                    store.setName(json_user.getString("name"));
                    store.setAddress(json_user.getString("address"));
                    store.setLatitude(json_user.getDouble("latitude"));
                    store.setLongitude(json_user.getDouble("longitude"));
                    store.setCategory_id(json_user.getInt("category_id"));
                    // store.setType(json_user.getInt("type"));
                    store.setStatus(json_user.getInt("status"));
                    store.setGallery(json_user.getInt("gallery"));

                    if (json_user.has("website"))
                        store.setWebsite(json_user.getString("website"));


                    try {
                        store.setLink(json_user.getString("link"));
                    } catch (Exception e) {
                    }

                    try {
                        store.setDistance(json_user.getDouble("distance"));
                    } catch (Exception e) {
                        store.setDistance(0.0);
                    }

                    store.setPhone(json_user.getString("telephone"));
                    store.setVoted(json_user.getBoolean("voted"));
                    store.setVotes((float) json_user.getDouble("votes"));
                    store.setNbr_votes(json_user.getString("nbr_votes"));

                    if (json_user.has("category_color") && !json_user.getString("category_color").equalsIgnoreCase("null"))
                        store.setCategory_color(json_user.getString("category_color"));

                    if (json_user.has("category_name") && !json_user.getString("category_name").equalsIgnoreCase("null"))
                        store.setCategory_name(json_user.getString("category_name"));

                    if (json_user.has("nbrProducts"))
                        store.setNbrProduct(json_user.getInt("nbrProducts"));


                    if (json_user.has("nbrOffers"))
                        store.setNbrOffers(json_user.getInt("nbrOffers"));

                    try {
                        store.setCanChat(json_user.getInt("canChat"));
                    } catch (Exception e) {
                    }

                    try {
                        store.setSaved(json_user.getInt("saved"));
                    } catch (Exception e) {
                    }

                    try {
                        store.setLastProduct(json_user.getString("lastProduct"));
                    } catch (Exception e) {
                        store.setLastProduct("");
                    }


                    try {
                        store.setUser_id(json_user.getInt("user_id"));
                    } catch (Exception e) {
                    }


                    try {
                        store.setFeatured(json_user.getInt("featured"));
                    } catch (Exception e) {
                    }


                    try {
                        store.setDescription(json_user.getString("description"));
                    } catch (Exception e) {
                        store.setDescription("");
                    }

                    try {
                        store.setDetail(json_user.getString("detail"));
                    } catch (Exception e) {
                        store.setDescription("");
                    }


                    try {
                        if (json_user.has("opening_time_table")) {
                            store.setOpening_time_table(json_user.getString("opening_time_table"));
                            JSONObject opt = new JSONObject(json_user.getString("opening_time_table"));

                            OpeningTimeTableParser optp = new OpeningTimeTableParser(opt);
                            store.setOpening_time_table_list(optp.getList());
                        } else {
                            store.setOpening_time_table("");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        store.setOpening_time_table("");
                    }

                    if (json_user.has("opening"))
                        store.setOpening(json_user.getInt("opening"));


                    JSONObject json_user_manger = new JSONObject(json_user.getString("user"));
                    UserParser mUserParserSender = new UserParser(json_user_manger);
                    User manager = mUserParserSender.getUser().get(0);
                    if (manager != null) {
                        store.setUser(manager);

                        if (AppContext.DEBUG)
                            Log.e("StoreParserManager", manager.getUsername() + "- " + manager.getId() + " sss " + store.getCategory_id());

                    }
                    String jsonValues = "";
                    try {

                        if (!json_user.isNull("images")) {
                            jsonValues = json_user.getJSONObject("images").toString();
                            JSONObject jsonObject = new JSONObject(jsonValues);
                            ImagesParser imgp = new ImagesParser(jsonObject);

                            if (imgp.getImagesList().size() > 0) {
                                store.setImages(imgp.getImagesList().get(0));
                                store.setListImages(imgp.getImagesList());
                                store.setImageJson(jsonObject.toString());
                            }


                        }

                    } catch (JSONException jex) {
                        store.setListImages(new RealmList<Images>());
                    }

                    list.add(store);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return list;
    }


}
