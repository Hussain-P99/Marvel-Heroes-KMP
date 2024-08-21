/**
 * Created by Hussain on 9/18/2022
 *
 */

package org.hsn.marvelheroes.model

sealed class ExecResult<T>(val data : T? =null, val message : String? =null){
    class Success<T>(data: T?) : ExecResult<T>(data)
    class Error<T>(message: String?) : ExecResult<T>(null,message)
    class Loading<T>(isLoading : Boolean = true) : ExecResult<T>(null,null)
}