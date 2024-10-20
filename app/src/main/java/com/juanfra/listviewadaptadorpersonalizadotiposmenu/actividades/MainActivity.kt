package com.juanfra.listviewadaptadorpersonalizadotiposmenu.actividades

import android.app.AlertDialog
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.juanfra.listviewadaptadorpersonalizadotiposmenu.R
import com.juanfra.listviewadaptadorpersonalizadotiposmenu.adaptadores.AdaptadorArrayAdapter
import com.juanfra.listviewadaptadorpersonalizadotiposmenu.datos.Datos

/*
Autor: Juan Francisco Sánchez González
Fecha: 20/10/2024
Clase: Actividad donde se utiliza un adaptador personalizado para asignar las opciones a un ListView,
y se asigna su manejador de eventos para controlar el click en sus elementos. También se implementan
un OptionsMenu y un ContextMenu sobre un control básico TextView.
*/

class MainActivity : AppCompatActivity() {

    // Controles a utilizar
    lateinit var listViewPersonalizado: ListView
    lateinit var etiqueta: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Crear y llenar la lista de datos a pasar al adaptador
        val listaDatos = listOf(
            Datos(getString(R.string.datos_tit1), getString(R.string.datos_desc1)),
            Datos(getString(R.string.datos_tit2), getString(R.string.datos_desc2)),
            Datos(getString(R.string.datos_tit3), getString(R.string.datos_desc3))
        )

        initListado(listaDatos)
        initEtiqueta()
    }

    // Instanciamos el objeto ListView y le asignamos el adaptador personalizado que nos permitirá
    // inflar los datos en la vista. Por último, se implementa su listener para controlar el click
    // en sus elementos.
    private fun initListado(datos: List<Datos>) {
        listViewPersonalizado = findViewById<ListView>(R.id.ListView)

        // Inflar la vista de la cabecera
        val headerView = layoutInflater.inflate(R.layout.cabecera_lista_personalizada, listViewPersonalizado, false)
        // Agregar la cabecera al ListView
        listViewPersonalizado.addHeaderView(headerView)

        // Adaptador
        val adaptador3 = AdaptadorArrayAdapter(this, datos)
        listViewPersonalizado.adapter = adaptador3

        // Manejador de eventos
        listViewPersonalizado.setOnItemClickListener { parent, view, position, id ->
            // Ajustar la posición debido a la cabecera (position - 1)
            val actualPosition = position - 1

            // Verificar que la posición sea válida
            if (actualPosition >= 0 && actualPosition < datos.size) {
                // Obtener el elemento de la lista en la posición pulsada
                val datosSeleccionados = datos[actualPosition]

                // Mostrar el valor de los campos del elemento pulsado del listado
                Toast.makeText(this, "${datosSeleccionados.texto1} ${datosSeleccionados.texto2}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Instanciamos el objeto TextView y le asignamos el menú contextual sobre el control
    private fun initEtiqueta() {
        etiqueta = findViewById(R.id.TextViewMenuContext)
        // Registrar el menú contextual
        registerForContextMenu(etiqueta)
    }

    // Inflar el menú de opciones
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.optionsmenu, menu)
        return true
    }

    // Manejar la selección de elementos del menú OptionsMenu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mnOp1 -> {
                // Acciones para "Opción 1"
                Toast.makeText(this, getString(R.string.op1_text), Toast.LENGTH_SHORT).show()
                true
            }
            R.id.mnOp2 -> {
                // Acciones para "Opción 2"
                Toast.makeText(this, getString(R.string.op2_text), Toast.LENGTH_SHORT).show()
                true
            }
            R.id.mnOp3 -> {
                // Acciones para "Opción 3"
                Toast.makeText(this, getString(R.string.op3_text), Toast.LENGTH_SHORT).show()
                true
            }
            R.id.mnOp4 -> {
                // Finalizar Actividad
                finish()
                true
            }
            // Acciones para el submenú
            R.id.sub_option1 -> {
                Toast.makeText(this, getString(R.string.op11_text), Toast.LENGTH_SHORT).show()
                true
            }
            R.id.sub_option2 -> {
                Toast.makeText(this, getString(R.string.op12_text), Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Inflar el menú contextual
    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.contextmenu, menu)
    }

    // Manejar la selección de elementos del menú contextual
    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mnCtx1 -> {
                // Acciones para "Opción 1"
                Toast.makeText(this, getString(R.string.op1_text), Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}