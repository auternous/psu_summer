fun computerPartsCatalog(block: ComputerPartsCatalog.() -> Unit): ComputerPartsCatalog {
    val catalog = ComputerPartsCatalog()
    catalog.block()
    return catalog
}

fun ComputerPartsCatalog.printParts() {
    println("Parts in the catalog:")
    partsList.forEach { println(it) }
}
fun ComputerPartsCatalog.catalog(block: ComputerPartsCatalog.() -> Unit) {
    this.block()
}
fun main() {
    val catalog = computerPartsCatalog {
        part {
            itemNumber = "1234"
            name = "CPU"
            manufacturer = "Intel"
            partType = "Processor"
            mainCharacteristic = "Core i7"
            price = 250.0
        }
        part {
            itemNumber = "67890"
            name = "GPU"
            manufacturer = "Nvidia"
            partType = "Graphics Card"
            mainCharacteristic = "GeForce RTX 3080"
            price = 800.0
        }
        part {
            itemNumber = "54321"
            name = "RAM"
            manufacturer = "Corsair"
            partType = "Memory"
            mainCharacteristic = "16GB DDR4"
            price = 100.0
        }
        part {
            itemNumber = "98765"
            name = "SSD"
            manufacturer = "Samsung"
            partType = "Storage"
            mainCharacteristic = "1TB NVMe"
            price = 150.0
        }
        part {
            itemNumber = "13579"
            name = "Keyboard"
            manufacturer = "Logitech"
            partType = "Accessory"
            mainCharacteristic = "Wireless"
            price = 50.0
        }
        part {
            itemNumber = "14579"
            name = "Keyboard"
            manufacturer = "BeQuiet"
            partType = "Memory"
            mainCharacteristic = "Wireless"
            price = 50.0
        }
    }
    // Инфиксный метод для поиска по номеру
    val cpu = catalog.findByItemNumberCustom("1234")
    println("Поиск по товарному номеру:")
    println(cpu)
    // Унарный оператор для получения размера каталога
    val catalogSize = +catalog
    println("Размер каталога: $catalogSize")
    // Бинарный оператор для объединения двух каталогов
    val otherCatalog = computerPartsCatalog {
        part {
            itemNumber = "24680"
            name = "Motherboard"
            manufacturer = "ASUS"
            partType = "Motherboard"
            mainCharacteristic = "ATX"
            price = 200.0
        }
    }
    val mergedCatalog = catalog + otherCatalog
    println("Объединенный каталог:")
    mergedCatalog.printParts()
    //Функция удаления части компьютера
    catalog.removePart(cpu!!)
    println("Удаление CPU из каталога:")
    catalog.printParts()
    // Функция высшего порядка для поиска по предикату
    val expensiveParts = catalog.findBy { price > 200.0 }
    println("Дорогие компоненты:")
    expensiveParts.forEach { println(it) }
    // Лямбда-параметр, являющийся расширением класса
    val someParts = catalog.findBy { manufacturer == "BeQuiet" }
    println("Компоненты BeQuiet:")
    someParts.forEach { println(it) }
    // Лямбда-параметр, являющийся расширением класса
    val intelParts = catalog.findBy { partType == "Memory" }
    println("Компоненты Памяти Memory:")
    intelParts.forEach { println(it) }
    while (true) {
        println("Выберите функцию для вызова:")
        println("1. Найти по номеру")
        println("2. Найти по производителю")
        println("3. Найти по типу")
        println("4. Собрать компьютер")
        println("5. Выйти")

        val choice = readLine()?.toIntOrNull()

        when (choice) {
            1 -> {
                println("Введите номер части:")
                val itemNumber = readLine()
                val part = catalog.findByItemNumber(itemNumber ?: "")
                println("Результат поиска:")
                println(part)
            }
            2 -> {
                println("Введите производителя:")
                val manufacturer = readLine()
                val parts = catalog.findByManufacturer(manufacturer ?: "")
                println("Результат поиска:")
                parts.forEach { println(it) }
            }
            3 -> {
                println("Введите тип части:")
                val partType = readLine()
                val parts = catalog.findByPartType(partType ?: "")
                println("Результат поиска:")
                parts.forEach { println(it) }
            }
            4 -> {
                val computerParts = catalog.assembleComputer()
                println("Результат сборки компьютера:")
                computerParts.forEach { println(it) }
            }
            5 -> return
            else -> println("Неверный выбор. Попробуйте еще раз.")
        }

        println()
    }
}

