package com.github.alexeygorovoy.moxytemplate.ui.common.moxy

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.arellomobile.mvp.MvpDelegate

open class MvpAppCompatFragment : Fragment() {

	private var mIsStateSaved: Boolean = false
	private var mMvpDelegate: MvpDelegate<out MvpAppCompatFragment>? = null

	/**
	 * @return The [MvpDelegate] being used by this Fragment.
	 */
	private var delegate: MvpDelegate<*>? = getMvpDelegate()

	private fun getMvpDelegate() = delegate ?: MvpDelegate(this).also { delegate = it }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		getMvpDelegate().onCreate(savedInstanceState)
	}

	override fun onStart() {
		super.onStart()

		mIsStateSaved = false

		getMvpDelegate().onAttach()
	}

	override fun onResume() {
		super.onResume()

		mIsStateSaved = false

		getMvpDelegate().onAttach()
	}

	override fun onSaveInstanceState(outState: Bundle) {
		super.onSaveInstanceState(outState)

		mIsStateSaved = true

		getMvpDelegate().onSaveInstanceState(outState)
		getMvpDelegate().onDetach()
	}

	override fun onStop() {
		super.onStop()

		getMvpDelegate().onDetach()
	}

	override fun onDestroyView() {
		super.onDestroyView()

		getMvpDelegate().onDetach()
		getMvpDelegate().onDestroyView()
	}

	override fun onDestroy() {
		super.onDestroy()

		//We leave the screen and respectively all fragments will be destroyed
		if (requireActivity().isFinishing) {
			getMvpDelegate().onDestroy()
			return
		}

		// When we rotate device isRemoving() return true for fragment placed in backstack
		// http://stackoverflow.com/questions/34649126/fragment-back-stack-and-isremoving
		if (mIsStateSaved) {
			mIsStateSaved = false
			return
		}

		// See https://github.com/Arello-Mobile/Moxy/issues/24
		var anyParentIsRemoving = false
		var parent = parentFragment
		while (!anyParentIsRemoving && parent != null) {
			anyParentIsRemoving = parent.isRemoving
			parent = parent.parentFragment
		}

		if (isRemoving || anyParentIsRemoving) {
			getMvpDelegate().onDestroy()
		}
	}
}