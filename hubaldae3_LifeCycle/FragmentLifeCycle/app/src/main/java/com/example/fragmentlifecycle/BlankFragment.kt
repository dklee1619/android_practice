package com.example.fragmentlifecycle

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.fragmentlifecycle.databinding.FragmentBlankBinding

class BlankFragment : Fragment() {
    var a: Int = 0

    //    private val binding by lazy {FragmentBlankBinding.inflate(layoutInflater)} // 프래그먼트는 액티비티와는 다르게 해야한다 정확한 원리까진 아직 모르더라도, 다르게 한다는거는 인지하고 가기.
    var _binding: FragmentBlankBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) { //
        super.onCreate(savedInstanceState)
        Log.d("Fragment.A", "onCreate1")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("Fragment.A", "onCreateView2")
//        Log.d("Fragment.A","전달된 데이터 :${arguments?.getString("key")}")
        _binding = FragmentBlankBinding.inflate(inflater, container, false)
//        return inflater.inflate(R.layout.fragment_blank, container, false)
//        return FragmentBlankBinding.inflate(layoutInflater).root // 프래그먼트의 최상위 뷰로 수정!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
        Log.d("Fragment.A", "onViewCreated3")

    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d("Fragment.A", "onViewStateRestored4")
    }

    override fun onStart() {
        super.onStart()
        Log.d("Fragment.A", "onStart5")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Fragment.A", "onResume6")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Fragment.A", "onPause7")
    }

    override fun onStop() {
        super.onStop()
        bind2()
        Log.d("Fragment.A", "onStop8")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        a++
        Log.d("Fragment.A", "onSaveInstanceState9")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onSaveInstanceState(Bundle()) // dk.. 애초에 그냥 호출하면 끝이였네.?
        _binding = null
        Log.d("Fragment.A", "onDestroyView10")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Fragment.A", "onDestroy11")
    }

    fun bind() {
        binding.fg1tv.text = "첫번째 프래그, ${a}번 재사용"
        binding.fg1bt.text = "전달된 데이터 : " + arguments?.getString("key", "")
        binding.bundledatatv.text = "프래그2->1 전달된 데이터 : " + arguments?.getString("text", "")
    }

    fun bind2() {
//        val bundle = Bundle() // 새로운 인스턴스를 생성하는대신
        val bundle = MainActivity.bundle // MainActivity에서 생성된 bundle을 사용하니까, 데이터 전달간에 의도하지 않던 동작이 해결되었다.
        bundle.putString("text", binding.bundledataet.text.toString())
        (activity as MainActivity).BlankFragment2.arguments = bundle
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