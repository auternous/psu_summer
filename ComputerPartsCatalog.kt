class ComputerPartsCatalog {
    val partsList = mutableListOf<ComputerPart>()

    fun part(block: ComputerPartBuilder.() -> Unit) {
        val builder = ComputerPartBuilder()
        builder.block()
        partsList.add(builder.build())
    }

    fun findByItemNumber(itemNumber: String): ComputerPart? {
        return partsList.find { it.itemNumber == itemNumber }
    }

    fun findByManufacturer(manufacturer: String): List<ComputerPart> {
        return partsList.filter { it.manufacturer == manufacturer }
    }

    fun findByPartType(partType: String): List<ComputerPart> {
        return partsList.filter { it.partType == partType }
    }

    fun assembleComputer(): List<ComputerPart> {
        return partsList.filter { it.partType != "Accessory" }
    }

    infix fun findByItemNumberCustom(itemNumber: String): ComputerPart? {
        return findByItemNumber(itemNumber)
    }

    operator fun unaryPlus(): Int {
        return partsList.size
    }

    operator fun plus(catalog: ComputerPartsCatalog): ComputerPartsCatalog {
        val mergedCatalog = ComputerPartsCatalog()
        mergedCatalog.partsList.addAll(partsList)
        mergedCatalog.partsList.addAll(catalog.partsList)
        return mergedCatalog
    }

    fun removePart(part: ComputerPart) {
        partsList.remove(part)
    }

    operator fun ComputerPartsCatalog.invoke(block: ComputerPartsCatalog.() -> Unit) {
        this.block()
    }

    fun findBy(block: ComputerPart.() -> Boolean): List<ComputerPart> {
        return partsList.filter(block)
    }
}