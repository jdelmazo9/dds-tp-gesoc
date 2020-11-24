package grupo6.bitacoraOperaciones;

public enum TipoOperacion {
    CREATE("CREATE"),
    UPDATE("UPDATE"),
    DELETE("DELETE");

    public final String label;

    TipoOperacion(String label){
        this.label = label;
    }

    public static TipoOperacion valueOfLabel(String label) {
        for (TipoOperacion t : values()) {
            if (t.label.equals(label)) {
                return t;
            }
        }
        return null;
    }
}
