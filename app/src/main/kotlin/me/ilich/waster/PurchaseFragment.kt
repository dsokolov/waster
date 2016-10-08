package me.ilich.waster

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.onClick

/**
 * Created by ilich on 08.10.16.
 */

class PurchaseFragment : Fragment() {

    companion object {

        fun create(): PurchaseFragment = PurchaseFragment()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_purchase, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById(R.id.button).onClick {
            (activity as MainActivity).billingController.foo()
        }
    }

}
