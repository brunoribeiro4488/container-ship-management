package lapr.project.utils.dto;


import java.util.Objects;

public class AuditTrailDTO {

    /**
     * The operation's date.
     */
    private final String date;
    /**
     * The operation's type.
     */
    private final String operation;
    /**
     * The container number.
     */
    private final String containeNumber;
    /**
     * The container ISO code.
     */
    private final String containerIsoCode;
    /**
     * The cargo manifest ID.
     */
    private final String cargoManifestID;
    /**
     * The cargo manifest partial ID.
     */
    private final String cargoManifestPartialID;
    /**
     * The cargo manifest type.
     */
    private final String cargoManifestType;

    public AuditTrailDTO(String date, String operation, String containeNumber, String containerIsoCode, String cargoManifestID, String cargoManifestPartialID, String cargoManifestType) {
        this.date = date;
        this.operation = operation;
        this.containeNumber = containeNumber;
        this.containerIsoCode = containerIsoCode;
        this.cargoManifestID = cargoManifestID;
        this.cargoManifestPartialID = cargoManifestPartialID;
        this.cargoManifestType = cargoManifestType;
    }

    /**
     * Return a textual representation of the object, which contains all of its attributes.
     *
     * @return auditTrailDTO characteristics
     */
    @Override
    public String toString() {
        return String.format("Cargo Manifest ID = %s || Cargo Manifest Partial ID = %s || Cargo Manifest Type = %s || Container Number = %s || Container ISO Code = %s || Date = %s || Operation = %s", cargoManifestID, cargoManifestPartialID, cargoManifestType, containeNumber, containerIsoCode, date, operation);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuditTrailDTO that = (AuditTrailDTO) o;
        return  Objects.equals(date, that.date) ;
    }

}
