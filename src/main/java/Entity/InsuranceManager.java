package Entity;

import jakarta.persistence.*;

import java.util.Collection;

/**
 * @author
 * @version ${}
 * @created 25/04/2024 14:10
 * @project InsuranceManagementApplication
 */
@Entity
@Table(name = "INSURANCE_MANAGER", schema = "public", catalog = "postgres")
public class InsuranceManager {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private String id;
    @Basic
    @Column(name = "full_name")
    private String fullName;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "address")
    private String address;
    @Basic
    @Column(name = "phone_number")
    private String phoneNumber;
    @OneToMany(mappedBy = "insuranceManager")
    private Collection<Claim> listOfClaims;
    @OneToMany(mappedBy = "insuranceManager")
    private Collection<InsuranceSurveyor> listOfSurveyors;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InsuranceManager that = (InsuranceManager) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (fullName != null ? !fullName.equals(that.fullName) : that.fullName != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(that.phoneNumber) : that.phoneNumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        return result;
    }

    public Collection<Claim> getListOfClaims() {
        return listOfClaims;
    }

    public void setListOfClaims(Collection<Claim> listOfClaims) {
        this.listOfClaims = listOfClaims;
    }

    public Collection<InsuranceSurveyor> getListOfSurveyors() {
        return listOfSurveyors;
    }

    public void setListOfSurveyors(Collection<InsuranceSurveyor> listOfSurveyors) {
        this.listOfSurveyors = listOfSurveyors;
    }
}