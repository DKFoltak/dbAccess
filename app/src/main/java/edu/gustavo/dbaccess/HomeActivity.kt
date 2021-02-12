package edu.gustavo.dbaccess

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.gustavo.dbaccess.dataAccess.ClientFactory
import edu.gustavo.dbaccess.dataAccess.ClientViewModel
import kotlinx.coroutines.flow.observeOn

class HomeActivity : AppCompatActivity() {

    val clientModel : ClientViewModel by viewModels{ ClientFactory(application) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(findViewById(R.id.toolbar))

        setTitle(R.string.HomeTitle)
    }
}