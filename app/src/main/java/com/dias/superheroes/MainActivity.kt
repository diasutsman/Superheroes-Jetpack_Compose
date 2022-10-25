package com.dias.superheroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dias.superheroes.model.Hero
import com.dias.superheroes.ui.theme.SuperheroesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperheroesTheme {
                SuperheroApp(HeroesDataSource.heroes)
            }
        }
    }
}

@Composable
fun SuperheroApp(listHeroes: List<Hero>) {
    Scaffold(
        topBar = {
            HeroAppbar()
        }
    ) {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(listHeroes) { hero ->
                HeroItem(hero)
            }
        }
    }
}

@Composable
fun HeroAppbar() {
    Surface(
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth(),
        color = MaterialTheme.colors.background,
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.h1,
            modifier = Modifier.wrapContentSize(Alignment.Center)
        )
    }
}

@Composable
fun HeroItem(hero: Hero, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = 2.dp,
        shape = RoundedCornerShape(16.dp),
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
                .height(74.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Column(
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = stringResource(hero.nameRes),
                    style = MaterialTheme.typography.h3
                )
                Text(
                    text = stringResource(hero.descriptionRes),
                    style = MaterialTheme.typography.body1
                )
            }
            Image(
                painter = painterResource(hero.imageRes),
                contentDescription = null,
                modifier = Modifier.clip(
                    shape = RoundedCornerShape(8.dp),
                ).aspectRatio(1f),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SuperheroItemPreview() {
    SuperheroesTheme {
        HeroItem(Hero(R.string.hero1, R.string.description1, R.drawable.android_superhero3))
    }
}

@Preview(showBackground = true)
@Composable
fun SuperheroAppPreview() {
    SuperheroesTheme {
        SuperheroApp(HeroesDataSource.heroes)
    }
}