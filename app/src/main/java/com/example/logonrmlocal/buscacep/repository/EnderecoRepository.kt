package com.example.logonrmlocal.buscacep.repository

import com.example.logonrmlocal.buscacep.api.getEnderecoService
import com.example.logonrmlocal.buscacep.model.Endereco
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.CountedCompleter


class EnderecoRepository{

    fun buscar(cep:String,
               onCompleter:(Endereco?)->Unit,
               onError:(Throwable?)->Unit){
       getEnderecoService()
               .buscar(cep)
               .enqueue(object: Callback<Endereco> {
                   override fun onFailure(call: Call<Endereco>?, t: Throwable?) {
                       onError(t)

                   }

                   override fun onResponse(call: Call<Endereco>?, response: Response<Endereco>?) {

                    if(response?.isSuccessful ==true) {
                        onCompleter(response?.body())
                    }else{
                        onError(Throwable(response?.errorBody()?.string()))
                    }
                    }

               })
    }
}