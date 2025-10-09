package com.example.gamer.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Notifications
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

data class Game(
    val id: Int,
    val title: String,
    val platform: String,
    val price: String,
    val imageRes: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreScreen(
    onNavigateToNews: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateBack: () -> Unit,
    showSnack: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableStateOf(1) }
    val isDarkMode = isSystemInDarkTheme()
    val primaryColor = Color(0xFFE91E63)
    val barColor = if (isDarkMode) Color.Black else primaryColor
    val backgroundColor = if (isDarkMode) Color(0xFF121212) else Color(0xFFF5F5F5)
    val cardBackground = if (isDarkMode) Color(0xFF1E1E1E) else Color.White
    val textColor = if (isDarkMode) Color.White else Color.Black
    val secondaryTextColor = if (isDarkMode) Color.White.copy(alpha = 0.6f) else Color.Gray

    val games = remember {
        listOf(
            Game(
                1,
                "Red Dead Redemption 2",
                "PS4 Games",
                "70.0 $",
                R.drawable.readdead2
            ),
            Game(
                2,
                "Grand Theft Auto 5",
                "PS4 Games",
                "30.0 $",
                R.drawable.gta
            ),
            Game(
                3,
                "The Legend of Zelda",
                "Nintendo Switch",
                "90.0 $",
                R.drawable.zelda
            ),

            )

    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Store",
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
                    IconButton(onClick = { showSnack("Cart") }) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Cart",
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
                    onClick = {
                        selectedTab = 0
                        onNavigateToNews()
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
                    onClick = { selectedTab = 1 },
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
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showSnack("Add new game") },
                containerColor = primaryColor,
                contentColor = Color.White
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(backgroundColor)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }

            items(games) { game ->
                GameCard(
                    game = game,
                    isDarkMode = isDarkMode,
                    onAddToCart = {
                        showSnack("${game.title} added to cart")
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun GameCard(
    game: Game,
    isDarkMode: Boolean,
    onAddToCart: () -> Unit
) {
    val cardBackground = if (isDarkMode) Color(0xFF1E1E1E) else Color.White
    val textColor = if (isDarkMode) Color.White else Color.Black
    val secondaryTextColor = if (isDarkMode) Color.White.copy(alpha = 0.6f) else Color.Gray
    val primaryColor = Color(0xFFE91E63)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = cardBackground)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Game Image
            Card(
                modifier = Modifier.size(100.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Image(
                    painter = painterResource(id = game.imageRes),
                    contentDescription = game.title,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Game Info
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = game.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = primaryColor
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = game.platform,
                    fontSize = 14.sp,
                    color = secondaryTextColor
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = game.price,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = textColor
                )
            }

            // Add to Cart Button
            IconButton(
                onClick = onAddToCart,
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = primaryColor
                )
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Add to cart",
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}