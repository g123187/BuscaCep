package com.example.logonrmlocal.buscacep.ui.pesquisar

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.logonrmlocal.buscacep.model.Endereco
import com.example.logonrmlocal.buscacep.repository.EnderecoRepository

class PesquisaViewModel : ViewModel(){

    val enderecoRepository= EnderecoRepository()
    val endereco = MutableLiveData<Endereco>()
    val menssagemError = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()

    fun buscar(cep:String){
        isLoading.value = true
        enderecoRepository.buscar(
                cep,
                onCompleter = {
                    endereco.value = it
                    menssagemError.value = ""
                    isLoading.value = false
                },
                onError = {
                    endereco.value = null
                    menssagemError.value = it?.message
                    isLoading.value = false

                }
        )

    }
}