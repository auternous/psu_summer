class ComputerPartBuilder {
    lateinit var itemNumber: String
    lateinit var name: String
    lateinit var manufacturer: String
    lateinit var partType: String
    lateinit var mainCharacteristic: String
    var price: Double = 0.0

    fun build(): ComputerPart {
        return ComputerPart(itemNumber, name, manufacturer, partType, mainCharacteristic, price)
    }
}