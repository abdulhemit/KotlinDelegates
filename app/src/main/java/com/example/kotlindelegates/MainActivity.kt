package com.example.kotlindelegates

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

class MainActivity : AppCompatActivity(),LifecycleLogger by LifecycleLoggerImplemetation() {
   private val myVariable by lazy {

       println("hollo this is a lazy implemetation")
       10
   }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerLifecycleOwner(this)


        println(myVariable)
    }


}
interface LifecycleLogger{
    fun registerLifecycleOwner(owner: LifecycleOwner)
}
class LifecycleLoggerImplemetation : LifecycleLogger,LifecycleEventObserver{
    override fun registerLifecycleOwner(owner: LifecycleOwner) {
        owner.lifecycle.addObserver(this)
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when(event){
            Lifecycle.Event.ON_RESUME -> println("On Resume EXecuted")
            Lifecycle.Event.ON_PAUSE -> println("On Pause EXecuted")
            else -> Unit
        }
    }

}