package com.picpay.desafio.android.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.ui.adapter.UserListAdapter

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var adapter: UserListAdapter
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    override fun onResume() {
        super.onResume()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initView()
        initViewModel()
    }

    private fun initView(){
        adapter = UserListAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun initViewModel(){
        viewModel = ViewModelProvider(this)
            .get(MainViewModel::class.java)
        viewModel.fetchUsers()
        viewModel.users.observe(this, Observer { users ->
            users?.let {
                adapter.setUser(it)
            }
        })
    }


}
