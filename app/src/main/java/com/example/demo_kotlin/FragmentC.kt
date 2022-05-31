package com.example.demo_kotlin

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentC.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentC : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        Log.d("thinhvh", "${this.javaClass.name} onCreate: ")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d("thinhvh", "${this.javaClass.name} onCreateView: ")
        return inflater.inflate(R.layout.fragment_c, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentC.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentC().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("thinhvh", "${this.javaClass.name} onViewCreated: ")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d("thinhvh", "${this.javaClass.name} onViewStateRestored: ")
    }

    override fun onStart() {
        super.onStart()
        Log.d("thinhvh", "${this.javaClass.name} onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d("thinhvh", "${this.javaClass.name} onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d("thinhvh", "${this.javaClass.name} onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d("thinhvh", "${this.javaClass.name} onStop: ")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("thinhvh", "${this.javaClass.name} onSaveInstanceState: ")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("thinhvh", "${this.javaClass.name} onDestroyView: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("thinhvh", "${this.javaClass.name} onDestroy: ")
    }
}