package com.example.daggerusingroomdatabasewithmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.daggerusingroomdatabasewithmvvm.database.UserEntity
import com.example.daggerusingroomdatabasewithmvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    lateinit var viewModel : MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.savebtn.setOnClickListener {
           val userEntity =  UserEntity(name = binding.enterEdit.text.toString())

            viewModel.insertRecord(userEntity)
            binding.resultText.setText("")

        }

        initViewModel()
    }

    private fun initViewModel(){

        viewModel =  ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getRecordsObserver().observe(this, object : Observer<List<UserEntity>>{
            override fun onChanged(t: List<UserEntity>?) {
                binding.resultText.setText("")
               t?.forEach {
                   binding.resultText.append(it.name + "\n")
               }
            }
        })
    }
}