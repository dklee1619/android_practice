package com.example.a6_5listview2

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.Manifest // << 이거 추가됨 이거 추가됨 이거 추가됨 이거 추가됨 이거 추가됨 이거 추가됨
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a6_5listview2.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), MyAdapter.OnItemClickListener {
    private lateinit var binding: ActivityMainBinding
    companion object {
        const val REQUEST_CODE_READ_CONTACTS = 1001
    }

    private lateinit var adapter: MyAdapter
    private lateinit var adapter2: MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS), REQUEST_CODE_READ_CONTACTS)
        }

//        val contactsList = mutableListOf<Contact>()

//        val cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null)
//        cursor?.let {
//            while (it.moveToNext()) {
//                val name = it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
//                val phoneNumber = it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
//                contactsList.add(Contact(name, phoneNumber))
//            }
//            it.close()
//        }
        // 데이터 원본 준비
        val dataList = mutableListOf<Contact>()
        dataList.add(Contact(R.drawable.sample_0, "가가가", "010-0000-0000",false))
        dataList.add(Contact(R.drawable.sample_1, "나나나", "010-0000-0000",false))
        dataList.add(Contact(R.drawable.sample_2, "다다다", "010-0000-0000",false))
        dataList.add(Contact(R.drawable.sample_3, "라라라", "010-0000-0000",false))
        dataList.add(Contact(R.drawable.sample_4, "마마마", "010-0000-0000",false))
        dataList.add(Contact(R.drawable.sample_5, "바바바", "010-0000-0000",false))
        dataList.add(Contact(R.drawable.sample_6, "사사사", "010-0000-0000",false))
        dataList.add(Contact(R.drawable.sample_7, "아아아", "010-0000-0000",false))
        dataList.add(Contact(R.drawable.sample_8, "자자자", "010-0000-0000",false))
        dataList.add(Contact(R.drawable.sample_9, "차차차", "010-0000-0000",false))
        dataList.add(Contact(R.drawable.sample_10, "카카카", "010-0000-0000",false))

        adapter = MyAdapter(dataList,this,1)
        adapter2 = MyAdapter(dataList,this,2)
        binding.recyclerView.adapter = adapter // 1
        binding.recyclerView.layoutManager = LinearLayoutManager(this) // 1

        binding.recyclerViewfavorite.adapter = adapter2 // 1
        binding.recyclerViewfavorite.layoutManager = LinearLayoutManager(this) // 1

    }    override fun onFavoriteImageViewClicked(position: Int, item: Contact) {
        item.isFavorite = !(item.isFavorite)
        adapter.notifyItemChanged(position)
        adapter2.notifyItemChanged(position)
        // 아이템 클릭 시 동작하는 코드
    }
}