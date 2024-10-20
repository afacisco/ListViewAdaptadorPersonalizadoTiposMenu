package com.juanfra.listviewadaptadorpersonalizadotiposmenu.adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.juanfra.listviewadaptadorpersonalizadotiposmenu.R
import com.juanfra.listviewadaptadorpersonalizadotiposmenu.datos.Datos

/*
Autor: Juan Francisco Sánchez González
Fecha: 20/10/2024
Clase: Adaptador Personalizado que infla los datos en la vista del listado.
*/

// Se especifica el constructor principal, que acepta un context y una lista de datos (List<Datos>).
// ArrayAdapter<Datos>(context, 0, datos) llama al constructor de la superclase ArrayAdapter con los parámetros necesarios.
class AdaptadorArrayAdapter(context: Context, datos: List<Datos>) : ArrayAdapter<Datos>(context, 0, datos) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Utilizamos el operador elvis (?:) para verificar si convertView es nulo.
        // Si lo es, infla la vista con LayoutInflater; de lo contrario, utiliza la vista reciclada.
        val itemView = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_lista_personalizada, parent, false)

        // Obtener el elemento de datos actual
        val datos = getItem(position)

        // Actualizar los TextView con la información del objeto 'Datos'
        val textView1 = itemView.findViewById<TextView>(R.id.texto1)
        val textView2 = itemView.findViewById<TextView>(R.id.texto2)

        textView1.text = datos?.texto1
        textView2.text = datos?.texto2

        // Retornar la vista actualizada
        return itemView
    }

}
