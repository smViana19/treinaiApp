package br.com.samuel.treinaiapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import br.com.samuel.treinaiapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    val navHostFragment =
      supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    val navController = navHostFragment.navController
    binding.bottomNavigationAppBar.setupWithNavController(navController)

    navController.addOnDestinationChangedListener { _, destination, _ ->
      val isRootDestination = destination.id in getRootDestinations()
      binding.topAppBar.isVisible = !isRootDestination
      with(binding.topAppBar) {
        if (!isRootDestination) {
          setNavigationIcon(R.drawable.ic_arrow_back_24)
          setNavigationOnClickListener { navController.navigateUp() }
        } else {
          navigationIcon = null
        }
      }

      when (destination.id) {
        R.id.homeFragment -> {
          binding.topAppBar.title = "Home"
          binding.topAppBar.visibility = View.GONE
        }

        R.id.workoutListFragment -> {
          binding.topAppBar.title = "Meus treinos"
          binding.topAppBar.visibility = View.VISIBLE
          binding.bottomNavigationAppBar.visibility = View.VISIBLE
          binding.topAppBar.setBackgroundColor(
            ContextCompat.getColor(
              baseContext,
              R.color.white
            )
          )
          binding.topAppBar.setNavigationIconTint(getColor(R.color.black))
        }

        R.id.workoutDetailsFragment -> {
          binding.topAppBar.title = null
          binding.topAppBar.visibility = View.VISIBLE
          binding.bottomNavigationAppBar.visibility = View.GONE
          binding.topAppBar.setBackgroundResource(R.drawable.background_primary_gradient)
          binding.topAppBar.setNavigationIconTint(getColor(R.color.white))
        }

        R.id.addExerciseFragment -> {
          binding.topAppBar.title = null
          binding.topAppBar.visibility = View.VISIBLE
          binding.bottomNavigationAppBar.visibility = View.GONE
          binding.topAppBar.setBackgroundColor(getColor(R.color.white))
          binding.topAppBar.setNavigationIconTint(getColor(R.color.black))
        }
      }
    }
  }

  private fun getRootDestinations(): Set<Int> {
    return setOf(
      R.id.homeFragment,
    ) //TODO: ADD MAIS TELAS QUE NAO VAO TER A TOPBAR
  }

}