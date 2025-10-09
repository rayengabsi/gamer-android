package com.example.gamer.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gamer.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(
    onNavigateBack: () -> Unit,
    onNavigateToStore: () -> Unit,
    onNavigateToProfile: () -> Unit,
    showSnack: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableStateOf(0) }
    val primaryColor = MaterialTheme.colorScheme.primary
    val backgroundColor = MaterialTheme.colorScheme.background
    val surfaceColor = MaterialTheme.colorScheme.surface
    val onSurfaceColor = MaterialTheme.colorScheme.onSurface
    val isDarkMode = isSystemInDarkTheme()
    val barColor = if (isDarkMode) Color.Black else primaryColor

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "News",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                actions = {
                    IconButton(onClick = { showSnack("Notifications") }) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notifications",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = barColor
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = barColor
            ) {
                NavigationBarItem(
                    icon = {
                        Icon(
                            Icons.Default.Notifications,
                            contentDescription = "News",
                            tint = Color.White
                        )
                    },
                    label = {
                        Text(
                            "News",
                            color = Color.White
                        )
                    },
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        selectedTextColor = Color.White,
                        unselectedIconColor = Color.White.copy(alpha = 0.6f),
                        unselectedTextColor = Color.White.copy(alpha = 0.6f),
                        indicatorColor = Color.Transparent
                    )
                )

                NavigationBarItem(
                    icon = {
                        Icon(
                            Icons.Default.ShoppingCart,
                            contentDescription = "Store",
                            tint = Color.White
                        )
                    },
                    label = {
                        Text(
                            "Store",
                            color = Color.White
                        )
                    },
                    selected = selectedTab == 1,
                    onClick = {
                        selectedTab = 1
                        onNavigateToStore()
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        selectedTextColor = Color.White,
                        unselectedIconColor = Color.White.copy(alpha = 0.6f),
                        unselectedTextColor = Color.White.copy(alpha = 0.6f),
                        indicatorColor = Color.Transparent
                    )
                )

                NavigationBarItem(
                    icon = {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = "Profile",
                            tint = Color.White
                        )
                    },
                    label = {
                        Text(
                            "Profile",
                            color = Color.White
                        )
                    },
                    selected = selectedTab == 2,
                    onClick = {
                        selectedTab = 2
                        onNavigateToProfile()
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        selectedTextColor = Color.White,
                        unselectedIconColor = Color.White.copy(alpha = 0.6f),
                        unselectedTextColor = Color.White.copy(alpha = 0.6f),
                        indicatorColor = Color.Transparent
                    )
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .background(backgroundColor)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            // Top Gaming News Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = surfaceColor
                )
            ) {
                Column {
                    Image(
                        painter = painterResource(id = R.drawable.top_gaming_news),
                        contentDescription = "Top Gaming News",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentScale = ContentScale.Crop
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(surfaceColor)
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Top 10 Gaming News of the day",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = onSurfaceColor
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Here's the latest news from the gaming and e-sports world.",
                            fontSize = 14.sp,
                            color = onSurfaceColor.copy(alpha = 0.6f),
                            lineHeight = 20.sp
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            TextButton(onClick = { showSnack("Show More clicked") }) {
                                Text(
                                    text = "Show More",
                                    color = primaryColor,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Zelda Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = surfaceColor
                )
            ) {
                Column {
                    Image(
                        painter = painterResource(id = R.drawable.zelda_game),
                        contentDescription = "The Legend of Zelda",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentScale = ContentScale.Crop
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(surfaceColor)
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "The Legend of Zelda",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = onSurfaceColor
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Here's the latest news from the gaming and e-sports world.",
                            fontSize = 14.sp,
                            color = onSurfaceColor.copy(alpha = 0.6f),
                            lineHeight = 20.sp
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            TextButton(onClick = { showSnack("Show More clicked") }) {
                                Text(
                                    text = "Show More",
                                    color = primaryColor,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}