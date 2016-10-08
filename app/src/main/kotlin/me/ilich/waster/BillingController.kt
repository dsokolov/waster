package me.ilich.waster

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import com.android.vending.billing.IInAppBillingService
import org.json.JSONObject
import java.util.*

class BillingController(
        val context: Context
) {

    var mService: IInAppBillingService? = null

    var mServiceConn: ServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName) {
            mService = null
        }

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            mService = IInAppBillingService.Stub.asInterface(service)
        }
    }

    fun onCreate() {
        val serviceIntent = Intent("com.android.vending.billing.InAppBillingService.BIND")
        serviceIntent.setPackage("com.android.vending")
        context.bindService(serviceIntent, mServiceConn, Context.BIND_AUTO_CREATE)
    }

    fun onDestroy() {
        if (mService != null) {
            context.unbindService(mServiceConn)
        }
    }

    fun foo() {
        if (mService != null) {
            val skuList = ArrayList<String>()
            skuList.add("premiumUpgrade")
            skuList.add("gas")
            val querySkus = Bundle()
            querySkus.putStringArrayList("ITEM_ID_LIST", skuList);
            val skuDetails = mService?.getSkuDetails(3, context.packageName, "inapp", querySkus)
            val response = skuDetails?.getInt("RESPONSE_CODE")
            if (response == 0) {
                val responseList = skuDetails?.getStringArrayList("DETAILS_LIST")
                if (responseList != null) {
                    for (thisResponse in responseList) {
                        val `object` = JSONObject(thisResponse)
                        val sku = `object`.getString("productId")
                        val price = `object`.getString("price")
                        if (sku == "premiumUpgrade") {

                        } else if (sku == "gas") {

                        }
                    }
                }
            }
        }
    }

    fun purchased() {
        val ownedItems = mService?.getPurchases(3, context.getPackageName(), "inapp", null)

        val response = ownedItems?.getInt("RESPONSE_CODE")
        if (response == 0) {
            val ownedSkus = ownedItems?.getStringArrayList("INAPP_PURCHASE_ITEM_LIST")
            val purchaseDataList = ownedItems?.getStringArrayList("INAPP_PURCHASE_DATA_LIST")
            val signatureList = ownedItems?.getStringArrayList("INAPP_DATA_SIGNATURE_LIST")
            val continuationToken = ownedItems?.getString("INAPP_CONTINUATION_TOKEN")

            if (purchaseDataList != null) {
                for (i in 0..purchaseDataList.size - 1) {
                    val purchaseData = purchaseDataList.get(i)
                    val signature = signatureList?.get(i)
                    val sku = ownedSkus?.get(i)

                    // do something with this purchase information
                    // e.g. display the updated list of products owned by user
                }

                // if continuationToken != null, call getPurchases again
                // and pass in the token to retrieve more items
            }
        }

    }

}