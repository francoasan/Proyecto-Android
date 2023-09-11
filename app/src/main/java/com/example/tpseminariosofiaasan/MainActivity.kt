package com.example.tpseminariosofiaasan

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tpseminariosofiaasan.R.layout.activity_main
import com.example.tpseminariosofiaasan.UserApplication.Companion.preferencias

class MainActivity : AppCompatActivity() {

    lateinit var etNombreUsuario:EditText
    lateinit var etPasswordUsuario:EditText
    lateinit var btnIniciarSesion:Button
    lateinit var cbRecordar:CheckBox


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)

        etNombreUsuario = findViewById(R.id.etNombreUsuario)
        etPasswordUsuario = findViewById(R.id.etPasswordUsuario)
        cbRecordar = findViewById(R.id.checkboxRecordar)
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion)

        btnIniciarSesion.setOnClickListener{
            val enteredUsername = etNombreUsuario.text.toString()
            val enteredPassword = etPasswordUsuario.text.toString()

            if(enteredUsername.isEmpty() || enteredPassword.isEmpty()){
                Toast.makeText(this, "Faltan datos", Toast.LENGTH_LONG).show()
            } else {
                if(cbRecordar.isChecked && checkCredentials(enteredUsername, enteredPassword)){
                    preferencias.saveUsername(enteredUsername)
                    preferencias.savePassword(enteredPassword)
                    val intentUsuarioCorrecto1 = Intent(this, listaDeElementos::class.java)
                    startActivity(intentUsuarioCorrecto1)
                }

                if(checkCredentials(enteredUsername, enteredPassword)){
                    val intentUsuarioCorrecto = Intent(this, listaDeElementos::class.java)
                    startActivity(intentUsuarioCorrecto)
                } else {
                    Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_LONG).show()
                }
            }
        }

        //iniciarListaElementos()
        checkUserValues()

       /* val botonIniciarSesion = findViewById<Button>(R.id.btnIniciarSesion)
        botonIniciarSesion.setOnClickListener{
            var textoEtNombreUsuario = etNombreUsuario.text.toString()
            var textoEtPasswordUsuario = etPasswordUsuario.text.toString()

            if(textoEtNombreUsuario.isEmpty() || textoEtPasswordUsuario.isEmpty()){
                Toast.makeText(this, "Faltan datos", Toast.LENGTH_LONG).show()
            } else {
                if(cbRecordar.isChecked){

                }
                val intent = Intent(this, listaDeElementos::class.java)
                startActivity(intent)

            }

        }*/

        val botonNecesitasUnaCuenta = findViewById<Button>(R.id.botonNecesitasUnaCuenta)
        botonNecesitasUnaCuenta.setOnClickListener{
            val intent2= Intent(this, pantallaRegistro::class.java)
            startActivity(intent2)
        }
    }

    /*private fun startListaElementos(usuarioGuardado: String) {
        val intent = Intent(this, listaDeElementos::class.java)
        intent.putExtra("Nombre", usuarioGuardado)
        startActivity(intent)
    }*/

    fun checkCredentials(username: String, password: String):Boolean{
        val db = UsuariosDatabase.getDatabase(this)
        val userDao = db.PersonaDao()
        val user = userDao.getUserByUsername(username)

        return (user != null && user.password == password)
    }

    fun checkUserValues(){
        if(preferencias.getUsername().isNotEmpty() && preferencias.getPassword().isNotEmpty()){
            goToDetail()
        }
    }


  /*  fun iniciarListaElementos(){
        val botonIniciarSesion = findViewById<Button>(R.id.btnIniciarSesion)
        botonIniciarSesion.setOnClickListener{
            accessToDetail()
        }
    }
*/
   /* fun accessToDetail(){
        var textoEtNombreUsuario = etNombreUsuario.text.toString()
        var textoEtPasswordUsuario = etPasswordUsuario.text.toString()
        cbRecordar = findViewById(R.id.checkboxRecordar)
        if(textoEtNombreUsuario.isNotEmpty() || textoEtPasswordUsuario.isNotEmpty()){
            if(cbRecordar.isChecked){
                //Acceder recordando usuario
                preferencias.saveUsername(textoEtNombreUsuario)
                preferencias.savePassword(textoEtPasswordUsuario)
                goToDetail()
            } else{
                //Acceder sin recordar
                goToDetail()
            }
        } else {
            Toast.makeText(this, "Faltan datos", Toast.LENGTH_LONG).show()
        }
    }
*/
    fun goToDetail(){
        startActivity(Intent(this, listaDeElementos::class.java))
    }
}