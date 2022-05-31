package com.example.demo_kotlin

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.demo_kotlin.viewmodel.AModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentA.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentA : Fragment() {
    private val model by viewModels<AModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("thinhvh", "${this.javaClass.name} onCreate: ")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view : View=  inflater.inflate(R.layout.fragment_a, container, false)

        val navController = findNavController()

       var button:View = view.findViewById(R.id.btn);
        button.setOnClickListener {
            navController.navigate(R.id.fragmentB)
        }
        Log.d("thinhvh", "${this.javaClass.name} onCreateView: ")
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            FragmentA()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("thinhvh", "${this.javaClass.name} onViewCreated: ")

        model.data.observe(viewLifecycleOwner, Observer {
            Log.d("thinhvh", "data observe: $it")
        })

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