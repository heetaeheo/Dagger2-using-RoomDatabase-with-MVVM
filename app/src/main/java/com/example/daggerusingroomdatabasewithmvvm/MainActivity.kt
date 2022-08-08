package com.example.daggerusingroomdatabasewithmvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.daggerusingroomdatabasewithmvvm.database.UserEntity
import com.example.daggerusingroomdatabasewithmvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val component get() = (application as MyApp).appComponent

    private lateinit var binding: ActivityMainBinding

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            component.provideViewModelFactory()
        )[MainActivityViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.savebtn.setOnClickListener { onSaveButtonClick() }

        initViewModel()
    }

    private fun onSaveButtonClick() {
        val userEntity = UserEntity(name = binding.enterEdit.text.toString())

        viewModel.insertRecord(userEntity)
        binding.resultText.text = ""
    }


    private fun initViewModel() {
        viewModel.allUserList.observe(this) { user ->
            binding.resultText.text = ""
            user?.forEach {
                binding.resultText.append(it.name + "\n")
            }
        }
    }
}