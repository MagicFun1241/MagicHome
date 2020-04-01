package com.mfsoftware.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.mfsoftware.home.adapters.Category
import com.mfsoftware.home.adapters.CategoryDataAdapter
import com.mfsoftware.home.decorators.GridSpacingItemDecoration
import kotlinx.android.synthetic.main.activity_add_device.*

class AddDeviceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_device)

        val list = ArrayList<Category>()
        list.add(Category(this, R.string.category_wifi, R.drawable.ic_wifi))
        list.add(Category(this, R.string.category_music, R.drawable.ic_music_note))
        list.add(Category(this, R.string.category_music, R.drawable.ic_music_note))

        list.add(Category(this, R.string.category_wifi, R.drawable.ic_wifi))
        list.add(Category(this, R.string.category_music, R.drawable.ic_music_note))
        list.add(Category(this, R.string.category_music, R.drawable.ic_music_note))

        category_list.layoutManager = GridLayoutManager(this, 3)
        category_list.adapter = CategoryDataAdapter(this, list)
        category_list.addItemDecoration(GridSpacingItemDecoration(this, 5))

        add_device_cancel.setOnClickListener {
            finish()
        }
    }
}
