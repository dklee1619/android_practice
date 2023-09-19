package com.example.fragmentlifecycle

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.example.fragmentlifecycle.databinding.FragmentBlank2Binding
import com.example.fragmentlifecycle.databinding.FragmentBlankBinding

class BlankFragment2 : Fragment() {
    var _binding: FragmentBlank2Binding? = null
    private val binding get() = _binding!!
    var a:Int = 0
    //
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Fragment.B", "onCreate1")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBlank2Binding.inflate(layoutInflater)
        Log.d("Fragment.B", "onCreateView2")
        //        return inflater.inflate(R.layout.fragment_blank, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
        Log.d("Fragment.B", "onViewCreated3")

    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d("Fragment.B", "onViewStateRestored4")
    }

    override fun onStart() {
        super.onStart()
        Log.d("Fragment.B", "onStart5")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Fragment.B", "onResume6")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Fragment.B", "onPause7")
    }

    override fun onStop() {
        super.onStop()
        bind2()
        Log.d("Fragment.B", "onStop8")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        a++
        Log.d("Fragment.B", "onSaveInstanceState9")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onSaveInstanceState(Bundle())
        _binding = null
        Log.d("Fragment.B", "onDestroyView10")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Fragment.B", "onDestroy11")
    }
    fun bind(){
        binding.fg1tv.text = "두번째 프래그, ${a}번 재사용"
        binding.fg1bt.text = "전달된 데이터 : " + arguments?.getString("key","")
        binding.bundledatatv.text = "프래그1->2 전달된 데이터 : " + arguments?.getString("text","")
    }
    fun bind2(){
//        val bundle = Bundle() // 새로운 인스턴스를 생성하는대신
        val bundle = MainActivity.bundle // MainActivity에서 생성된 bundle을 사용하니까, 데이터 전달간에 의도하지 않던 동작이 해결되었다.
        bundle.putString("text",binding.bundledataet.text.toString())
        (activity as MainActivity).BlankFragment.arguments = bundle
    }
}
/*
`**onCreate**`
`**onCreateView**`
`**onViewCreated**`
`**onViewRestored**`
`**onStart**`
`**onResume**`
`**onPause**`
`**onStop**`
`**onSaveInstanceState**`
`**onDestroyView**`
`**onDestroy**`
 */