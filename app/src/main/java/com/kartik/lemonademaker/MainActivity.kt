package com.kartik.lemonademaker

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kartik.lemonademaker.ui.theme.LemonadeMakerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeMakerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Lemonade()
                }
            }
        }
    }
}

@SuppressLint("ResourceType")
@Composable
fun Lemonade() {

    var currentStep by remember { mutableIntStateOf(1)}
    var squeezeCount by remember {
        mutableIntStateOf(0)
    }
    Surface {
        when(currentStep){
            1 -> {
                ScreenAll(R.drawable.lemon_tree,
                    R.string.lemon_content_description1.toString(),
                    R.string.lemon_resource1,
                    onImageClick = {
                        currentStep = 2
                        squeezeCount = (2..3).random()
                    })
            }
            2 -> {
                ScreenAll(R.drawable.lemon_squeeze,
                    R.string.lemon_content_description2.toString(),
                    R.string.lemon_resource2,
                    onImageClick = {
                        squeezeCount--
                        if (squeezeCount == 0) {
                            currentStep = 3
                        }
                    })
            }
            3 -> {
                ScreenAll(R.drawable.lemon_drink,
                    R.string.lemon_content_description2.toString(),
                    R.string.lemon_resource2,
                    onImageClick = {
                        currentStep = 4
                    })
            }
            4 -> {
                ScreenAll(R.drawable.lemon_restart,
                    R.string.lemon_content_description2.toString(),
                    R.string.lemon_resource2,
                    onImageClick = {
                        currentStep = 1
                    })
            }
        }
    }
}

@Composable
fun ScreenAll(resId: Int, resCd: String, textId: Int, onImageClick: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = onImageClick, shape = RoundedCornerShape(20.dp))
        {
            Image(
                painter = painterResource(resId),
                contentDescription = resCd,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = stringResource(textId),
            fontSize = 18.sp)
    }
}


@Preview(showSystemUi = true)
@Composable
fun LemonadeApp() {
    LemonadeMakerTheme {
        Lemonade()
    }
}