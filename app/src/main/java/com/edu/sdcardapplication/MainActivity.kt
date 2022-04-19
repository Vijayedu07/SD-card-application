package com.edu.sdcardapplication

import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {
    var etFileName: EditText? = null
    var etName: EditText? = null
    var etCGPA: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btn_WriteData).setOnClickListener {
            writeData()
        }
        findViewById<Button>(R.id.btn_ReadData).setOnClickListener {
            readData()
        }
    }

    private fun readData() {
        etName = findViewById(R.id.et_Name)
        etCGPA = findViewById(R.id.et_CGPA)
        etFileName = findViewById(R.id.et_fileName)
        var c: Int
        var count: Int = 0
        var temp: String = ""
        try{
            val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val myFile: File = File("${path}/"+etFileName!!.text.toString())
            val fin = FileInputStream(myFile)
//            while((fin.read())!=-1){
//                c = fin.read()
//                temp += (Char(c)).toString()
//            }

            while(fin.read().let{
                c=it; it!=-1
                }){
                temp += (Char(c)).toString()
            }
            fin.close()
            var splitValue = temp.split(",")
            Log.i("VIJ",temp.toString())
            etName!!.setText(splitValue[0])
            etCGPA!!.setText(splitValue[1])

        }
        catch (e: Exception){
            e.printStackTrace()
            Toast.makeText(this,"Read operation failed successfully because ${e.toString()}",Toast.LENGTH_SHORT).show()
        }

    }

    private fun writeData() {
        etFileName = findViewById(R.id.et_fileName)
        etName = findViewById(R.id.et_Name)
        etCGPA = findViewById(R.id.et_CGPA)
        var c: Int
        var temp: String? = null
        try{

            val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val myFile: File = File("${path}/"+etFileName!!.text.toString())
            val fos = FileOutputStream(myFile)
//            val fos: FileOutputStream = openFileOutput(etFileName!!.text.toString(), MODE_PRIVATE)
            Log.d("VIJ",myFile.absolutePath)
            val message: String = etName!!.text.toString() + "," + etCGPA!!.text.toString()
            Log.d("VIJ DEMO MESSAGE",message)
            Log.d("VIJ BYTE ARRAY MESSAGE", message.toByteArray().toString())
            fos.write(message.toByteArray())
            Toast.makeText(this,"Write operation executed successfully",Toast.LENGTH_SHORT).show()
            etName!!.setText("")
            etCGPA!!.setText("")
            fos.close()

        }
        catch (e: Exception){
            e.printStackTrace()
            Toast.makeText(this,"Write operation executed successfully because ${e.toString()}",Toast.LENGTH_SHORT).show()
        }
    }
}