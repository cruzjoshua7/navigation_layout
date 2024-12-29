package com.example.babysittercompose.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import com.example.babysittercompose.R

//Main app navigation per app
@Composable
fun BabySitterNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = BabySitterScreen.BabySitterMainScreen.route
    ) {
        composable(route = BabySitterScreen.BabySitterMainScreen.route) {
            BabySitterMainScreen(navController = navController)
        }
        composable(route = BabySitterScreen.YourChildDetails.route) {
            YourChildDetailsScreen(navController = navController)
        }
        composable(route = BabySitterScreen.TakeAPhotoScreen.route) {
            TakeAPhotoScreen(navController = navController)
        }
        composable(route = BabySitterScreen.ChildListScreen.route) {
            ChildListScreen(navController = navController, children)
        }
        composable(route = BabySitterScreen.FilterScreen.route) {
            FilterScreen(navController = navController)
        }
        composable(route = BabySitterScreen.SearchScreen.route) {
            SearchScreen(navController = navController)
        }
    }
}

@Composable
fun BabySitterMainScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Image
        Image(
            painter = painterResource(R.drawable.baby_sitter_mother_child),
            contentDescription = "Home Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(bottom = 16.dp)
        )

        // Title
        Text(
            text = "Babysitter",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 8.dp),
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )

        // Description
        Text(
            text = "Welcome to babysitty an app to schedule a babysitter for your little one. Write reviews and filter through are list of babysitters to find the perfect match for your child and date ",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 24.dp),
            textAlign = TextAlign.Center
        )
        //Button
        Button(
            onClick = {
                navController.navigate(BabySitterScreen.YourChildDetails.route)
            }
        ) {
            Text(text = "Lets Go")
        }
    }
}

@Composable
fun YourChildDetailsScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var isMale by remember { mutableStateOf(true) }
    var age by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(
            text = "Your child",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Name Entry
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // Gender Checkbox
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text(text = "Gender:", modifier = Modifier.padding(end = 8.dp))
            Checkbox(
                checked = isMale,
                onCheckedChange = { isMale = it },
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(text = if (isMale) "Male" else "Female")
        }

        // Age Entry
        OutlinedTextField(
            value = age,
            onValueChange = {
                if (it.all { char -> char.isDigit() }) {
                    val inputAge = it.toIntOrNull() ?: 0
                    if (inputAge in 0..17) age = it // Ensures the age is within the allowed range
                }
            },
            label = { Text("Age") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // Submit Button (Optional)
        Button(
            onClick = {
                navController.navigate(BabySitterScreen.TakeAPhotoScreen.route)
            }
        ) {
            Text(text = "Next")
        }
    }
}

@Composable
fun TakeAPhotoScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Title
        Row {
            // put icon images if needed
            Text(
                text = "Take a photo",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        // Image placeholder
        Image(
            painter = painterResource(id = R.drawable.cam_placeholder), // Replace with your drawable resource
            contentDescription = "Cam Placeholder",
            modifier = Modifier
                .size(150.dp)
                .background(Color.Gray) // Optional: Background for the placeholder
        )

        // Button
        Button(onClick = {
            // need to add cam functionallty
            navController.navigate(BabySitterScreen.ChildListScreen.route)
        }) {
            Icon(
                painter = painterResource(id = R.drawable.cam_icon), // Use your custom drawable
                contentDescription = "Camera Icon",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

data class Child(
    val name: String,
    var age:Int,
    val imageResId: Int // Drawable resource ID for the image
)

val children = listOf<Child>(
    Child("Chris", 22, R.drawable.cam_placeholder),
    Child("Rebecca", 22, R.drawable.cam_placeholder),
    Child("Jill", 22, R.drawable.cam_placeholder),
    Child("Leon", 22, R.drawable.cam_placeholder),
)

@Composable
fun ChildListScreen(navController: NavController, children: List<Child>, ) {

    Column {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable {
                    navController.navigate(BabySitterScreen.YourChildDetails.route)
                }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.plus_user),
                    contentDescription = "Child Image",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(16.dp))

                // Child Name
                Text(
                    text = "New Child",
                    modifier = Modifier.weight(1f)
                )
            }
        }
        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)) {
            items(children) { child ->
                ChildCard(child = child)
            }
        }
        Button(
            onClick = {
                navController.navigate(BabySitterScreen.FilterScreen.route)
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        ) {
            Text(text = "Next")
        }
    }
}

@Composable
fun ChildCard(child: Child) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Image in CircleCrop
            Image(
                painter = painterResource(id = child.imageResId),
                contentDescription = "Child Image",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))

            // Child Name
            Text(
                text = child.name,
                modifier = Modifier.weight(1f)
            )
            // Edit Icon
            IconButton(onClick = {  }) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Child")
            }
            // Delete Icon
            IconButton(onClick = {  }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Child")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterScreen(navController: NavController){
    var selectedOption by remember { mutableStateOf("Recommend") }
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(40.dp)) {
        // Title
        Text(text = "Filter Options")

        Spacer(modifier = Modifier.height(16.dp))

        // Dropdown menu
        Text("Sort By")

        // ExposedDropdownMenuBox is used for the dropdown
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            // TextField to show the selected option and the dropdown menu
            TextField(
                value = selectedOption,
                onValueChange = { },
                readOnly = true,
                label = { Text("Select Sorting Option") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                modifier = Modifier.fillMaxWidth()
            )

            // The dropdown itself
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {

            }
        }
        ParcelSlider("Price/Hour")
        Button(
            onClick = {
                navController.navigate(BabySitterScreen.SearchScreen.route)
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        ) {
            Text(text = "Apply")
        }
    }
}

@Composable
fun ParcelSlider(title: String) {
    var sliderValue by remember { mutableStateOf(0f) }
    Column {
        Slider(
            value = sliderValue,
            onValueChange = { sliderValue = it },
            valueRange = 0f..60f,
            modifier = Modifier.fillMaxWidth()
        )
        Text(text = "Value: ${sliderValue.toInt()}")
    }
}

data class Babysitter(
    val name: String,
    val location: String,
    val imageResId: Int,
    val rating: Double,
    val distance: Int,
    val costPerHour:Int
)

val babysitters = listOf<Babysitter>(
    Babysitter("Lee", "Corona",R.drawable.cam_placeholder,3.0,500,15 ),
    Babysitter("Alice", "Corona",R.drawable.cam_placeholder,3.0,500,15 ),
    Babysitter("Nina", "Corona",R.drawable.cam_placeholder,3.0,500,15 )

)
@Composable
fun SearchScreen(navController: NavController){
    Column(modifier = Modifier.fillMaxSize()) {
        // Top Image
        Image(
            painter = painterResource(id = R.drawable.cam_placeholder),
            contentDescription = "Top Image",
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )

        // Bar with two options: Favorites and Orders
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(modifier = Modifier
                .clickable {  },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorites", tint = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Favorites", color = Color.White)

            }
            Row(modifier = Modifier
                .clickable {  },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.List, contentDescription = "Orders", tint = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Orders", color = Color.White)
            }
        }

        // Bar with title "Babysitters" and filter icon
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Babysitters")
            Icon(modifier = Modifier
                .clickable {  },
                imageVector = Icons.Default.Menu, contentDescription = "Filter", tint = MaterialTheme.colorScheme.primary)
        }

        // Lazy list of babysitters
        LazyColumn(modifier = Modifier.weight(2f)) {
            items(babysitters) { babysitter ->
                BabysitterCard(babysitter = babysitter)
            }
        }
    }
}

@Composable
fun BabysitterCard(babysitter: Babysitter) {
// need to add popup menu
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)

    ) {
        Column{
            // Babysitter Image
            Image(
                painter = painterResource(id = babysitter.imageResId),
                contentDescription = "Babysitter Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Name and Location
            Text(
                text = babysitter.name,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Text(
                text = babysitter.location,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )

            // Rating, Distance, and Cost Per Hour
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Rating
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Star, contentDescription = "Rating", tint = Color.Yellow)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "${babysitter.rating}")
                }

                // Distance
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.LocationOn, contentDescription = "Distance", tint = Color.Gray)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "${babysitter.distance} m")
                }
                // Cost Per Hour
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "$${babysitter.costPerHour}/hr")
                }
            }
        }
    }
}


