interface VatProvider {

    double getDefaultVat();

    double getVatForType(String type);
}
