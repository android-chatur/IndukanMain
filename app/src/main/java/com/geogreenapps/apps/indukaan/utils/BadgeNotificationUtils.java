package com.geogreenapps.apps.indukaan.utils;

import android.graphics.drawable.Drawable;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentActivity;

import com.geogreenapps.apps.indukaan.R;
import com.geogreenapps.apps.indukaan.classes.Notification;
import com.geogreenapps.apps.indukaan.dtmessenger.MessengerHelper;
import com.mikepenz.actionitembadge.library.ActionItemBadge;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import static com.geogreenapps.apps.indukaan.activities.MainActivity.mainMenu;

public class BadgeNotificationUtils {


    public static void updateMessengerBadge(FragmentActivity activity) {

        try {

            if (MessengerHelper.NbrMessagesManager.getNbrTotalMessages() > 0) {
                Drawable msgIcon = new IconicsDrawable(activity)
                        .icon(CommunityMaterial.Icon.cmd_comment_multiple_outline)
                        .color(ResourcesCompat.getColor(activity.getResources(), R.color.color_toolbar_action, null))
                        .sizeDp(18);
                ActionItemBadge.update(activity, mainMenu.findItem(R.id.messenger_action), msgIcon,
                        ActionItemBadge.BadgeStyles.RED,
                        MessengerHelper.NbrMessagesManager.getNbrTotalMessages());
            } else {
                ActionItemBadge.hide(mainMenu.findItem(R.id.messenger_action));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void updateNotificationBadge(FragmentActivity activity) {

        try {

            Drawable pinIcon = new IconicsDrawable(activity)
                    .icon(CommunityMaterial.Icon.cmd_bell_outline)
                    .color(ResourcesCompat.getColor(activity.getResources(), R.color.color_toolbar_action, null))
                    .sizeDp(18);

            if (Notification.notificationsUnseen > 0) {
                ActionItemBadge.update(activity, mainMenu.findItem(R.id.notification_action), pinIcon,
                        ActionItemBadge.BadgeStyles.RED, Notification.notificationsUnseen);
            } else {
                ActionItemBadge.hide(mainMenu.findItem(R.id.notification_action));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void updateCartItemsBadge(FragmentActivity activity) {

        /*try {

            int itemCounter  = 0;
            if (SessionsController.isLogged() && CartController.productCartCounter(SessionsController.getSession().getUser().getId()) > 0) {
                itemCounter = CartController.productCartCounter(SessionsController.getSession().getUser().getId());
            }

                Drawable pinIcon = new IconicsDrawable(activity)
                        .icon(CommunityMaterial.Icon.cmd_cart_outline)
                        .color(ResourcesCompat.getColor(activity.getResources(), R.color.color_toolbar_action, null))
                        .sizeDp(18);


                if (itemCounter > 0) {
                    ActionItemBadge.update(activity, mainMenu.findItem(R.id.cart_icon), pinIcon,
                            ActionItemBadge.BadgeStyles.RED, itemCounter);
                } else {
                    ActionItemBadge.update(activity, mainMenu.findItem(R.id.cart_icon), pinIcon,
                            ActionItemBadge.BadgeStyles.GREY, Integer.MIN_VALUE);
                }




        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }


}
