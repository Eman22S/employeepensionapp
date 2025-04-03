/*package com.miu.pension;

public class PensionPlan {

}

package com.miu.pension;

import java.time.LocalDate;

public class PensionPlan {
    private String planReferenceNumber;
    private LocalDate enrollmentDate;
    private double monthlyContribution;

    // Constructors, Getters, Setters
}
*/
package com.miu.pension;

import java.time.LocalDate;

public class PensionPlan {
    private String planReferenceNumber;
    private LocalDate enrollmentDate;
    private double monthlyContribution;

    // ✅ Add this constructor
    public PensionPlan(String planReferenceNumber, LocalDate enrollmentDate, double monthlyContribution) {
        this.planReferenceNumber = planReferenceNumber;
        this.enrollmentDate = enrollmentDate;
        this.monthlyContribution = monthlyContribution;
    }

    // ✅ Add Getters
    public String getPlanReferenceNumber() {
        return planReferenceNumber;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public double getMonthlyContribution() {
        return monthlyContribution;
    }
}
