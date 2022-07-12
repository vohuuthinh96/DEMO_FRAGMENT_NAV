package com.example.demo_kotlin

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

class MainActivity : AppCompatActivity() {
    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var textView: TextView
    private var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Fragment nav
        //A qua B
        //A gọi vào pause -> stop -> destroyview -> lúc này viewmodel vẫn còn data, không cần phải load lại nữa,
        //B back về AA -> onCreateView -> onViewCreated->onStart->onResume -> lúc này viewmodel tự động bắn về data cũ mà không cần load lại, viewmodel cũng không cần khởi tạo lại
//
//        button1 = findViewById(R.id.button1)
//        button2 = findViewById(R.id.button2)
////        textView = findViewById(R.id.tv)
//
//
//        var id = Thread.currentThread().id
//        Log.d("thinhvh", "MainThread: $id")
//        button1.setOnClickListener {
//
//            // Dispatchers.Main vẫn chạy ở main thread
//            // Dispatchers.IO chạy ở thread khác
//            GlobalScope.launch(Dispatchers.Main) {
//                runOnUiThread(Runnable {
//                    textView.text = "loading"
//                })
////                delay(3000)
//                Thread.sleep(3000)
//                var id = Thread.currentThread().id
//                Log.d("thinhvh", "worker thread: $id")
//                runOnUiThread(Runnable {
//                    textView.text = "load done"
//                })
//
//            }
//        }
//
//        button2.setOnClickListener {
//            GlobalScope.launch(Dispatchers.Main + handler) {
////                fetchAndShowUser()
//
////                val userOne = async(Dispatchers.IO) { fetchFirstUser() }
////                val userTwo = async(Dispatchers.IO) { fetchSecondUser() }
////                showUsers(userOne.await(), userTwo.await())
//
////                Sử dụng withContext khi bạn không cần thực thi song song.
////                Chỉ sử dụng async khi bạn cần thực thi song song.
////                Cả withContext và async đều có thể được sử dụng để có được kết quả, không thể thực hiện được với launch.
////                Sử dụng withContext để trả về kết quả của một tác vụ.
////                Sử dụng async cho kết quả từ nhiều tác vụ chạy song song.
//
//
//                val userOne = withContext(Dispatchers.IO) { fetchFirstUser() }
//                val userTwo = withContext(Dispatchers.IO) { fetchSecondUser() }
//                showUsers(userOne, userTwo)
//            }
//        }
//
//
//    }
//
//    private fun showUsers(await: String, await1: String) {
//        textView.text = "$await $await1"
//    }
//
//
//    suspend fun fetchUser(): String {
//        return GlobalScope.async(Dispatchers.IO) {
//            // make network call
//            // return user
//            Thread.sleep(3000)
//            return@async "VO Huu Thinh"
//        }.await()
//    }
//
//    suspend fun fetchAndShowUser() {
//        val user = fetchUser() // fetch on IO thread
//        showUser(user) // back on UI thread
//    }
//
//    fun showUser(user: String) {
//        // show user
//        textView.text = user
//    }
//
//    fun fetchSecondUser(): String {
//        // make network call
//        // return user
//        return "Ten 2"
//    }
//
//    fun fetchFirstUser(): String {
//        // make network call
//        // return user
//        return "Ten 1"
//    }
//
//    val handler = CoroutineExceptionHandler { coroutineContext, throwable ->
//            Log.d("thinhvh", ": ${throwable.message}")
    }

    private val mResults = HashMap<String, Bundle>()
    private val mResultListeners = HashMap<String, LifecycleAwareResultListener>()

    fun setFragmentResult(requestKey: String, result: Bundle) {
        val resultListener: LifecycleAwareResultListener? = mResultListeners[requestKey]
        // nếu đã có listenner rồi thì bắn callback cho nó luôn và không add vào mResults nữa
        if (resultListener != null && resultListener.isAtLeast(Lifecycle.State.STARTED)) {
            resultListener.onFragmentResult(requestKey, result)
        } else {
            mResults.put(requestKey, result)
        }
    }

    fun setFragmentResultListener(requestKey: String, lifecycleOwner: LifecycleOwner, listener: FragmentResultListener) {
        val lifecycle = lifecycleOwner.lifecycle
        if (lifecycle.currentState == Lifecycle.State.DESTROYED) {
            // nếu trạng thái fragment là destroy thì không cho register
            return
        }

        val observer: LifecycleEventObserver = object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                if (event == Lifecycle.Event.ON_START) {
                    val storedResult = mResults[requestKey]
                    if (storedResult != null) {
                        listener.onFragmentResult(requestKey, storedResult)
                        clearFragmentResult(requestKey)
                    }
                }
                if (event == Lifecycle.Event.ON_DESTROY) {
                    lifecycle.removeObserver(this)
                    mResultListeners.remove(requestKey)
                }
            }
        }
        lifecycle.addObserver(observer)
        val storedListener: LifecycleAwareResultListener? = mResultListeners.put(requestKey, LifecycleAwareResultListener(lifecycle, listener, observer))
        storedListener?.removeObserver()
    }

    private class LifecycleAwareResultListener  constructor(
        private val mLifecycle: Lifecycle,
        private val mListener: FragmentResultListener,
        private val mObserver: LifecycleEventObserver
    ) : FragmentResultListener {
        fun isAtLeast(state: Lifecycle.State): Boolean {
            return mLifecycle.currentState.isAtLeast(state)
        }

        override fun onFragmentResult(requestKey: String, result: Bundle) {
            mListener.onFragmentResult(requestKey, result)
        }

        fun removeObserver() {
            mLifecycle.removeObserver(mObserver)
        }
    }

    fun clearFragmentResult(requestKey: String) {
        mResults.remove(requestKey)
    }
}