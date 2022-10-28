package com.example.humandisclass.Frament

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Matrix
import android.graphics.Typeface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.PermissionChecker
import androidx.core.content.PermissionChecker.checkSelfPermission
import com.example.humandisclass.Classifier
import com.example.humandisclass.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_scan.*
import kotlinx.android.synthetic.main.fragment_scan.view.*
import java.io.IOException

class ScanFragment : Fragment() {
    private val RESULT_LOAD_IMAGE = 123
    val IMAGE_CAPTURE_CODE = 654
    private val PERMISSION_CODE = 321
    var classifier: Classifier? = null
    private var image_uri: Uri? = null
    private lateinit var scanwithgallery:TextInputLayout
    private lateinit var scanwithcamera:TextInputLayout
    private lateinit var scanwithgalleryet:TextInputEditText
    private lateinit var scanwithcameraet:TextInputEditText
    private var innerImage:ImageView?=null
    var resultTv: TextView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         val view = inflater.inflate(R.layout.fragment_scan, container, false)
        scanwithcameraet= view.findViewById(R.id.scan_with_camera_edittext)
        scanwithgalleryet = view.findViewById(R.id.scan_with_gallery_edittext)
        scanwithcamera = view.findViewById(R.id.scan_with_camera)
        scanwithgallery =view.findViewById(R.id.scan_with_gallery)
        view.scan_text_1.visibility = VISIBLE
        view.allergy_image.visibility = VISIBLE
        view.lottie_scan_animation.visibility = VISIBLE
        view.frame_layout_scan.visibility = GONE
        view.scan_final_click.visibility = GONE
        scanwithcamera.visibility = VISIBLE
        scanwithgallery.visibility = VISIBLE
        innerImage = view.findViewById(R.id.image_scan_disease)
        resultTv = view.findViewById(R.id.disease_name_scan_final)
        scanwithgalleryet.setOnClickListener {
            val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE)
        }
        scanwithgallery.setOnClickListener {
              val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
              startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE)
          }
        scanwithcameraet.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(requireContext(),Manifest.permission.CAMERA) == PermissionChecker.PERMISSION_DENIED || checkSelfPermission(requireContext(),Manifest.permission.WRITE_EXTERNAL_STORAGE) == PermissionChecker.PERMISSION_DENIED) {
                    val permission = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    requestPermissions(permission, PERMISSION_CODE)
                } else {
                    openCamera()
                }
            } else {
                openCamera()
            }
            true
        }
          scanwithcamera.setOnClickListener {
              if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                  if (checkSelfPermission(requireContext(),Manifest.permission.CAMERA) == PermissionChecker.PERMISSION_DENIED || checkSelfPermission(requireContext(),Manifest.permission.WRITE_EXTERNAL_STORAGE) == PermissionChecker.PERMISSION_DENIED) {
                      val permission = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                      requestPermissions(permission, PERMISSION_CODE)
                  } else {
                      openCamera()
                  }
              } else {
                  openCamera()
              }
              true
          }

        //val typeface = Typeface.createFromAsset(requireActivity().assets,"sajal")
        //TODO initialize the Classifier class object. This class will load the model and using its method we will pass input to the model and get the output
        classifier = Classifier(requireActivity().assets,"model_unquant.tflite","labels.txt",224)


        //TODO ask for permission of camera upon first launch of application
        if (checkSelfPermission(requireContext(),Manifest.permission.CAMERA) == PermissionChecker.PERMISSION_DENIED ||
            checkSelfPermission(requireContext(),Manifest.permission.WRITE_EXTERNAL_STORAGE) == PermissionChecker.PERMISSION_DENIED) {
            val permission = arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            requestPermissions(permission, PERMISSION_CODE)
        }
             return view
    }

    private fun openCamera() {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "New Picture")
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera")
        image_uri = requireContext().contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri)
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
            if (requestCode == RESULT_LOAD_IMAGE && resultCode == Activity.RESULT_OK && data != null) {
                image_uri = data.data
                innerImage!!.setImageURI(image_uri)
                doInference()
            }
            if (requestCode == IMAGE_CAPTURE_CODE && resultCode == Activity.RESULT_OK ) {
                innerImage!!.setImageURI(image_uri)
                doInference()
            }

    }
    //TODO pass image to the model and shows the results on screen
    private fun doInference() {
        requireView().scan_text_1.visibility = GONE
        requireView().allergy_image.visibility = GONE
        requireView().lottie_scan_animation.visibility = GONE
        scanwithcamera.visibility = GONE
        scanwithgallery.visibility = GONE
        requireView().frame_layout_scan.visibility = VISIBLE
        requireView().scan_final_click.visibility = VISIBLE
        val input: Bitmap? = uriToBitmap(image_uri!!)
        val rotated = rotateBitmap(input!!)
        val res = classifier?.recognizeImage(rotated!!)
        resultTv?.text = ""
        for(r in res!!){
            resultTv?.append(r.title)
        }
    }
    //TODO takes URI of the image and returns bitmap
    private fun uriToBitmap(imageUri: Uri): Bitmap? {
        try {
            return MediaStore.Images.Media.getBitmap(
                requireContext().contentResolver, Uri.parse(
                    imageUri.toString()
                )
            )
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    //TODO rotate image if image captured on samsung devices
    //Most phone cameras are landscape, meaning if you take the photo in portrait, the resulting photos will be rotated 90 degrees.
    @SuppressLint("Recycle", "Range")
    private fun rotateBitmap(input: Bitmap): Bitmap? {
        val orientationColumn = arrayOf(MediaStore.Images.Media.ORIENTATION)
        val cur = requireContext().contentResolver.query(image_uri!!, orientationColumn, null, null, null)
        var orientation = -1
        if (cur != null && cur.moveToFirst()) {
            orientation = cur.getInt(cur.getColumnIndex(orientationColumn[0]))
        }
        Log.d("tryOrientation", orientation.toString() + "")
        val rotationMatrix = Matrix()
        rotationMatrix.setRotate(orientation.toFloat())
        return Bitmap.createBitmap(
            input,
            0,
            0,
            input.width,
            input.height,
            rotationMatrix,
            true
        )
    }

}