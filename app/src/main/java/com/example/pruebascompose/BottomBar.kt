package com.example.pruebascompose

import android.net.http.SslCertificate.saveState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy

@Composable
fun BottomBar() {
    val listItem = getBottomNavigationItems()
    val listIcon = {}
    NavigationBar( containerColor = colorResource(id = R.color.purple_200),
        tonalElevation = 4.dp,
        modifier = Modifier.height(70.dp)) {
        listItem.forEach { item ->
            val selected = false
            NavigationBarItem(
                selected = selected,
                onClick = {
                },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            modifier = Modifier.size(dimensionResource(R.dimen.size_120)),
                            painter = painterResource(
                                if (selected) item.selectedIcon else item.unselectedIcon
                            ),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                        Text(
                            style = MaterialTheme.typography.titleMedium,
                            text = stringResource(item.titleRes),
                            color = if (selected) colorResource(R.color.neutral_90)
                            else colorResource(R.color.neutral_95),
                            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                },
                label = null,
                alwaysShowLabel = false,
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

fun getBottomNavigationItems(): List<BottomBartems.BottomBarItem> {
    return listOf(
        BottomBartems.BottomBarItem.Home,
        BottomBartems.BottomBarItem.Search,
        BottomBartems.BottomBarItem.Settings
    )
}