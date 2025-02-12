package br.com.samuel.treinaiapp.ui.fragments

import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import br.com.samuel.treinaiapp.R
import br.com.samuel.treinaiapp.data.local.database.model.ExerciseModel
import br.com.samuel.treinaiapp.databinding.FragmentAddExerciseBinding
import br.com.samuel.treinaiapp.ui.viewmodel.AddExerciseViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class AddExerciseFragment : Fragment() {
  @Inject
  lateinit var addExerciseViewModel: AddExerciseViewModel
  private lateinit var binding: FragmentAddExerciseBinding

  private var selectedImagePath: String? = null
  private val pickImage = registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
    uri?.let {
      selectedImagePath = saveImageInternalStorage(it)
      loadImageIntoPreview(selectedImagePath)
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_add_exercise, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding = FragmentAddExerciseBinding.bind(view)
    binding.imageButtonSelectorImage.setOnClickListener {
      pickImage.launch(arrayOf("image/*"))
    }
    binding.buttonSaveExercise.setOnClickListener {
      saveExercise()
    }
  }

  private fun saveImageInternalStorage(uri: Uri): String {
    val inputStream = requireContext().contentResolver.openInputStream(uri)
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    val fileName = "IMAGE_${timeStamp}.jpg"
    println("filename: $fileName")
    val file = File(requireContext().filesDir, fileName)
    FileOutputStream(file).use { outputStream -> inputStream?.copyTo(outputStream) }
    println("file.absolutepath ${file.absolutePath}")
    return file.absolutePath
  }

  private fun loadImageIntoPreview(imagePath: String?) {
    imagePath?.let {
      val bitmap = BitmapFactory.decodeFile(it)
      binding.imageButtonSelectorImage.setImageBitmap(bitmap)
    }
  }

  private fun saveExercise() {
    val name = binding.editTextNameExercise.text.toString()
    val description = binding.editTextDetailsExercise.text.toString()
    val workoutId = arguments?.getInt("workoutId", 0) ?: 0
    val imagePath = selectedImagePath ?: ""
    val exercise = ExerciseModel(
      name = name,
      imageUrl = imagePath,
      description = description,
      workoutId = workoutId
    )

    if (name.isNotEmpty()) {
      addExerciseViewModel.insertExercise(exercise)
      Toast.makeText(context, "Exercicio salvo com sucesso", Toast.LENGTH_SHORT).show()
    } else {
      Toast.makeText(context, "Preencha o nome do exercicio", Toast.LENGTH_SHORT).show()
    }
  }


}