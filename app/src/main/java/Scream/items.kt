package Scream

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun Menu() {
    val context = LocalContext.current

    // Estados para las bandas, el multiplicador y la tolerancia
    var banda1 by remember { mutableStateOf<String?>(null) }
    var banda2 by remember { mutableStateOf<String?>(null) }
    var multiplicador by remember { mutableStateOf<String?>(null) }
    var tolerancia by remember { mutableStateOf<String?>(null) }

    var isExpanded1 by remember { mutableStateOf(false) }
    var isExpanded2 by remember { mutableStateOf(false) }
    var isExpanded3 by remember { mutableStateOf(false) }
    var isExpanded4 by remember { mutableStateOf(false) }

    var resistencia by remember { mutableStateOf("") }

    // Colores de las bandas seleccionadas
    var colorBanda1 by remember { mutableStateOf(Color.Transparent) }
    var colorBanda2 by remember { mutableStateOf(Color.Transparent) }
    var colorMultiplicador by remember { mutableStateOf(Color.Transparent) }
    var colorTolerancia by remember { mutableStateOf(Color.Transparent) }

    // Lista de colores y nombres de las bandas
    val bandasColores = listOf(
        Pair("Negro", Color.Black),
        Pair("Marrón", Color(0xFF8B4513)),
        Pair("Rojo", Color.Red),
        Pair("Naranja", Color(0xFFFFA500)),
        Pair("Amarillo", Color.Yellow),
        Pair("Verde", Color.Green),
        Pair("Azul", Color.Blue),
        Pair("Violeta", Color(0xFF8A2BE2)),
        Pair("Gris", Color.Gray),
        Pair("Blanco", Color.White)
    )

    // Lista de tolerancias
    val toleranciasColores = listOf(
        Pair("Marrón", Pair(1, Color(0xFF8B4513))),
        Pair("Rojo", Pair(2, Color.Red)),
        Pair("Verde", Pair(0.5, Color.Green)),
        Pair("Azul", Pair(0.25, Color.Blue)),
        Pair("Violeta", Pair(0.1, Color(0xFF8A2BE2))),
        Pair("Gris", Pair(0.05, Color.Gray)),
        Pair("Oro", Pair(5, Color(0xFFFFD700))),
        Pair("Plata", Pair(10, Color(0xFFC0C0C0)))
    )

    // Función para calcular la resistencia
    fun calcularResistencia() {
        if (banda1 != null && banda2 != null && multiplicador != null) {
            val valorBanda1 = banda1!!.toInt()
            val valorBanda2 = banda2!!.toInt()
            val valorMultiplicador = multiplicador!!.toFloat()

            val valorResistencia = (valorBanda1 * 10 + valorBanda2) * valorMultiplicador
            resistencia = "$valorResistencia Ω"
        } else {
            resistencia = "Seleccione todas las bandas"
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Banda 1
        ExposedDropdownMenuBox(
            expanded = isExpanded1,
            onExpandedChange = { isExpanded1 = !isExpanded1 }
        ) {
            TextField(
                value = banda1 ?: "Banda 1",
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(isExpanded1) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = isExpanded1,
                onDismissRequest = { isExpanded1 = false }
            ) {
                bandasColores.forEachIndexed { index, (nombre, color) ->
                    DropdownMenuItem(
                        text = {
                            Row {
                                Box(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .background(color)
                                )
                                Text("  $nombre - $index")
                            }
                        },
                        onClick = {
                            banda1 = "$index"
                            colorBanda1 = color
                            isExpanded1 = false
                            Toast.makeText(context, "Seleccionaste Banda 1: $nombre", Toast.LENGTH_SHORT).show()
                            calcularResistencia() // Actualiza la resistencia
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Banda 2
        ExposedDropdownMenuBox(
            expanded = isExpanded2,
            onExpandedChange = { isExpanded2 = !isExpanded2 }
        ) {
            TextField(
                value = banda2 ?: "Banda 2",
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(isExpanded2) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = isExpanded2,
                onDismissRequest = { isExpanded2 = false }
            ) {
                bandasColores.forEachIndexed { index, (nombre, color) ->
                    DropdownMenuItem(
                        text = {
                            Row {
                                Box(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .background(color)
                                )
                                Text("  $nombre - $index")
                            }
                        },
                        onClick = {
                            banda2 = "$index"
                            colorBanda2 = color
                            isExpanded2 = false
                            Toast.makeText(context, "Seleccionaste Banda 2: $nombre", Toast.LENGTH_SHORT).show()
                            calcularResistencia() // Actualiza la resistencia
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Multiplicador
        ExposedDropdownMenuBox(
            expanded = isExpanded3,
            onExpandedChange = { isExpanded3 = !isExpanded3 }
        ) {
            TextField(
                value = multiplicador ?: "Multiplicador",
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(isExpanded3) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = isExpanded3,
                onDismissRequest = { isExpanded3 = false }
            ) {
                val multiplicadores = listOf("1", "10", "100", "1k", "10k", "100k", "1M", "10M")
                multiplicadores.forEachIndexed { index, valor ->
                    DropdownMenuItem(
                        text = { Text(" $valor") },
                        onClick = {
                            multiplicador = valor.replace("k", "000").replace("M", "000000")
                            colorMultiplicador = bandasColores.getOrNull(index)?.second ?: Color.Transparent
                            isExpanded3 = false
                            Toast.makeText(context, "Seleccionaste Multiplicador: $valor", Toast.LENGTH_SHORT).show()
                            calcularResistencia() // Actualiza la resistencia
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Tolerancia
        ExposedDropdownMenuBox(
            expanded = isExpanded4,
            onExpandedChange = { isExpanded4 = !isExpanded4 }
        ) {
            TextField(
                value = tolerancia ?: "Tolerancia",
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(isExpanded4) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = isExpanded4,
                onDismissRequest = { isExpanded4 = false }
            ) {
                toleranciasColores.forEach { (nombre, pair) ->
                    DropdownMenuItem(
                        text = {
                            Row {
                                Box(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .background(pair.second)
                                )
                                Text("  $nombre - ${pair.first}%")
                            }
                        },
                        onClick = {
                            tolerancia = "${pair.first}%"
                            colorTolerancia = pair.second
                            isExpanded4 = false
                            Toast.makeText(context, "Seleccionaste Tolerancia: $nombre", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Mostrar las bandas de colores seleccionadas
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp, 20.dp)
                    .background(colorBanda1)
            )
            Box(
                modifier = Modifier
                    .size(50.dp, 20.dp)
                    .background(colorBanda2)
            )
            Box(
                modifier = Modifier
                    .size(50.dp, 20.dp)
                    .background(colorMultiplicador)
            )
            Box(
                modifier = Modifier
                    .size(50.dp, 20.dp)
                    .background(colorTolerancia)
            )
        }

        // Resultado de la resistencia
        Text(text = "Resistencia: $resistencia")
    }
}
