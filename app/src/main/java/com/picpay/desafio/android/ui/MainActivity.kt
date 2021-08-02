package com.picpay.desafio.android.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.databinding.ActivityMainBinding.inflate
import com.picpay.desafio.android.ui.adapter.UserListAdapter
import com.picpay.desafio.android.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)

        val userAdapter = UserListAdapter()

        binding.apply {
            recyclerView.apply {
                adapter = userAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
            }

            viewModel.users.observe(this@MainActivity) { result ->
                userAdapter.submitList(result.data)
                if (viewModel.listState != null) {
                    binding.recyclerView.layoutManager?.onRestoreInstanceState(viewModel.listState)
                }
                userListProgressBar.isVisible =
                    result is Resource.Loading && result.data.isNullOrEmpty()
                if(result is Resource.Error)
                    showErrorMessage()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.listState = binding.recyclerView.layoutManager?.onSaveInstanceState()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.listState = binding.recyclerView.layoutManager?.onSaveInstanceState()
    }

    fun showErrorMessage(){
        Toast.makeText(this, getString(R.string.error_message), Toast.LENGTH_LONG).show()
    }
}
