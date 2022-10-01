import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

class Student {
    String name;
    int roll_no;
    double CGPA;
    String Branch;
    String Status;
    int current_CTC;
    ArrayList<Company> offers_having;
    Company present;

    Student(String name, int roll, double cg, String branch, int ctc ){
        this.name = name;
        this.roll_no = roll;
        this.CGPA = cg;
        this.Branch = branch;
        this.current_CTC = ctc;
        this.Status = "unoffered";
        this.offers_having  = new ArrayList<Company>();

    }

    public void registered() {
        DateTimeFormatter forma = DateTimeFormatter.ofPattern("HH:mm dd/mm/yyyy");
        LocalDateTime dat = LocalDateTime.now();
        String forma_date = dat.format(forma);
        boolean flag = true;
        if (Integer.parseInt(PlacementCell.stud_reg_opening.substring(12, 16)) > Integer
                .parseInt(forma_date.substring(12, 16))) {
            flag = false;
        }
        if (Integer.parseInt(PlacementCell.stud_reg_opening.substring(12, 16)) < Integer
                .parseInt(forma_date.substring(12, 16))) {
            if (Integer.parseInt(PlacementCell.stud_reg_opening.substring(9, 11)) > Integer
                    .parseInt(forma_date.substring(9, 11))) {
                flag = false;
            }
            if (Integer.parseInt(PlacementCell.stud_reg_opening.substring(9, 11)) < Integer
                    .parseInt(forma_date.substring(9, 11))) {
                if (Integer.parseInt(PlacementCell.stud_reg_opening.substring(8, 10)) > Integer
                        .parseInt(forma_date.substring(8, 10))) {
                    flag = false;
                }
                if (Integer.parseInt(PlacementCell.stud_reg_opening.substring(8, 10)) < Integer
                        .parseInt(forma_date.substring(8, 10))) {
                    if (Integer.parseInt(PlacementCell.stud_reg_opening.substring(0, 2)) > Integer
                            .parseInt(forma_date.substring(0, 2))) {
                        flag = false;
                    }
                    if (Integer.parseInt(PlacementCell.stud_reg_opening.substring(0, 2)) < Integer
                            .parseInt(forma_date.substring(0, 2))) {
                        if (Integer.parseInt(PlacementCell.stud_reg_opening.substring(3, 5)) > Integer
                                .parseInt(forma_date.substring(3, 5))) {
                            flag = false;
                        }
                    }
                }
            }
        }

        if (Integer.parseInt(PlacementCell.stud_reg_closing.substring(12, 16)) < Integer
                .parseInt(forma_date.substring(12, 16))) {
            flag = false;
        }
        if (Integer.parseInt(PlacementCell.stud_reg_closing.substring(12, 16)) > Integer
                .parseInt(forma_date.substring(12, 16))) {
            if (Integer.parseInt(PlacementCell.stud_reg_closing.substring(9, 11)) < Integer
                    .parseInt(forma_date.substring(9, 11))) {
                flag = false;
            }
            if (Integer.parseInt(PlacementCell.stud_reg_closing.substring(9, 11)) > Integer
                    .parseInt(forma_date.substring(9, 11))) {
                if (Integer.parseInt(PlacementCell.stud_reg_closing.substring(8, 10)) < Integer
                        .parseInt(forma_date.substring(8, 10))) {
                    flag = false;
                }
                if (Integer.parseInt(PlacementCell.stud_reg_closing.substring(8, 10)) > Integer
                        .parseInt(forma_date.substring(8, 10))) {
                    if (Integer.parseInt(PlacementCell.stud_reg_closing.substring(0, 2)) < Integer
                            .parseInt(forma_date.substring(0, 2))) {
                        flag = false;
                    }
                    if (Integer.parseInt(PlacementCell.stud_reg_closing.substring(0, 2)) > Integer
                            .parseInt(forma_date.substring(0, 2))) {
                        if (Integer.parseInt(PlacementCell.stud_reg_closing.substring(3, 5)) < Integer
                                .parseInt(forma_date.substring(3, 5))) {
                            flag = false;
                        }
                    }
                }
            }
        }

        if (flag == false) {
            System.out.println("Registration Closed");

        } else {
            PlacementCell.reg_stud.add(this);
            System.out.println("Registered Succesfully!");
            System.out.println("Name: " + name);
            System.out.println("Roll no: " + roll_no);
            System.out.println("CGPA: " + CGPA);
            System.out.println("Branch: " + Branch);
        }
    }

    public boolean get_all_companies() {
        if (PlacementCell.comp_reg.size() == 0) {
            System.out.println("No Company available");
            return false;
        } else {
            for (int i = 0; i < PlacementCell.comp_reg.size(); i++) {
                System.out.println(PlacementCell.comp_reg.get(i).name + " "
                        + PlacementCell.comp_reg.get(i).package_offered + "LPA");
            }
            return true;
        }
    }

    public void register_for_company() {
        boolean xyey = get_all_companies();
        if (xyey == false) {
            return;
        } else {
            System.out.println("Give the index of the company");
            Scanner x = new Scanner(System.in);
            int index = x.nextInt();

            PlacementCell.comp_reg.get(index).register_student(this);
        }
    }

    public void get_status() {
        System.out.println(this.Status);
    }

    public void update_cgpa() {
        Scanner x = new Scanner(System.in);
        double new_cg = x.nextDouble();

        this.CGPA = new_cg;
    }

    public void accept() {
        if (this.offers_having.size() == 0) {
            System.out.println("No offers");
            return;
        }

        Company temp = this.offers_having.get(0);
        for (int i = 0; i < this.offers_having.size(); i++) {
            if (this.offers_having.get(i).package_offered > temp.package_offered) {
                temp = this.offers_having.get(i);
            }
        }
        if (this.current_CTC == 0) {
            PlacementCell.stud_having_offer.add(this);
        }
        if (this.current_CTC != 0) {
            this.present.student_selected.remove(this);
        }
        this.current_CTC = temp.package_offered;
        this.present = temp;
        this.Status = "Placed";
        this.offers_having.remove(temp);

        System.out.println("You have accepted the following offer:");
        System.out.println("company:" + temp.name);
        System.out.println("CTC" + temp.package_offered);
    }

    public void reject() {
        for (int i = 0; i < this.offers_having.size(); i++) {
            System.out.println(this.offers_having.get(i).name);
        }
        System.out.println("give index of offer you wanna reject");
        Scanner x = new Scanner(System.in);
        int to_reject = x.nextInt();
        Company comp_rejected = this.offers_having.get(to_reject);
        this.offers_having.remove(comp_rejected);

        if (this.current_CTC == 0 && this.offers_having.size() == 0) {
            this.Status = "Blocked";
            PlacementCell.blocked_stud.add(this);
            System.out.println(
                    "You have been blocked from Institute's Placement Process because you rejected the only offer you had!");
        }
    }
}

class Company {
    String name;
    String role_offering;
    int package_offered;
    double cg_req;
    ArrayList<Student> student_selected;
    ArrayList<Student> students_applied;


    Company(String name,String role_offering, int package_offered, double cg_req){
        this.name = name;
        this.role_offering = role_offering;
        this.package_offered = package_offered;
        this.cg_req = cg_req;
        this.student_selected  = new ArrayList<Student>();
        this.students_applied  = new ArrayList<Student>();
    }



    public void register_student(Student kuber) {
        // register student to this company

        if (kuber.current_CTC * 3 < this.package_offered | kuber.CGPA < this.cg_req | kuber.Status == "Placed"
                | kuber.Status == "Blocked") {
            System.out.println("Not Eligible!");
        } else {
            kuber.offers_having.add(this);
            students_applied.add(kuber);

            System.out.println("Registration successful!");
        }
    }

    public void register_to_institute_drive() {
        DateTimeFormatter forma = DateTimeFormatter.ofPattern("HH:mm dd/mm/yyyy");
        LocalDateTime dat = LocalDateTime.now();
        String forma_date = dat.format(forma);
        boolean flag = true;
        if (Integer.parseInt(PlacementCell.comp_reg_opening.substring(12, 16)) > Integer
                .parseInt(forma_date.substring(12, 16))) {
            flag = false;
        }
        if (Integer.parseInt(PlacementCell.comp_reg_opening.substring(12, 16)) < Integer
                .parseInt(forma_date.substring(12, 16))) {
            if (Integer.parseInt(PlacementCell.comp_reg_opening.substring(9, 11)) > Integer
                    .parseInt(forma_date.substring(9, 11))) {
                flag = false;
            }
            if (Integer.parseInt(PlacementCell.comp_reg_opening.substring(9, 11)) < Integer
                    .parseInt(forma_date.substring(9, 11))) {
                if (Integer.parseInt(PlacementCell.comp_reg_opening.substring(8, 10)) > Integer
                        .parseInt(forma_date.substring(8, 10))) {
                    flag = false;
                }
                if (Integer.parseInt(PlacementCell.comp_reg_opening.substring(8, 10)) < Integer
                        .parseInt(forma_date.substring(8, 10))) {
                    if (Integer.parseInt(PlacementCell.comp_reg_opening.substring(0, 2)) > Integer
                            .parseInt(forma_date.substring(0, 2))) {
                        flag = false;
                    }
                    if (Integer.parseInt(PlacementCell.comp_reg_opening.substring(0, 2)) < Integer
                            .parseInt(forma_date.substring(0, 2))) {
                        if (Integer.parseInt(PlacementCell.comp_reg_opening.substring(3, 5)) > Integer
                                .parseInt(forma_date.substring(3, 5))) {
                            flag = false;
                        }
                    }
                }
            }
        }

        if (Integer.parseInt(PlacementCell.comp_reg_closing.substring(12, 16)) < Integer
                .parseInt(forma_date.substring(12, 16))) {
            flag = false;
        }
        if (Integer.parseInt(PlacementCell.comp_reg_closing.substring(12, 16)) > Integer
                .parseInt(forma_date.substring(12, 16))) {
            if (Integer.parseInt(PlacementCell.comp_reg_closing.substring(9, 11)) < Integer
                    .parseInt(forma_date.substring(9, 11))) {
                flag = false;
            }
            if (Integer.parseInt(PlacementCell.comp_reg_closing.substring(9, 11)) > Integer
                    .parseInt(forma_date.substring(9, 11))) {
                if (Integer.parseInt(PlacementCell.comp_reg_closing.substring(8, 10)) < Integer
                        .parseInt(forma_date.substring(8, 10))) {
                    flag = false;
                }
                if (Integer.parseInt(PlacementCell.comp_reg_closing.substring(8, 10)) > Integer
                        .parseInt(forma_date.substring(8, 10))) {
                    if (Integer.parseInt(PlacementCell.comp_reg_closing.substring(0, 2)) < Integer
                            .parseInt(forma_date.substring(0, 2))) {
                        flag = false;
                    }
                    if (Integer.parseInt(PlacementCell.comp_reg_closing.substring(0, 2)) > Integer
                            .parseInt(forma_date.substring(0, 2))) {
                        if (Integer.parseInt(PlacementCell.comp_reg_closing.substring(3, 5)) < Integer
                                .parseInt(forma_date.substring(3, 5))) {
                            flag = false;
                        }
                    }
                }
            }
        }

        if (flag == false) {
            System.out.println("Registration Closed");

        } else {
            PlacementCell.comp_reg.add(this);
            System.out.println("Registered Succesfully!");
            System.out.println("Name: " + this.name);
            System.out.println("Role offered: " + this.role_offering);
            System.out.println("Package offered: " + this.package_offered);
            System.out.println("CGPA required: " + this.cg_req);
        }
    }

    public void get_selected_students() {
        if (this.student_selected.size() == 0) {
            System.out.println("no student selected");
            return;
        }
        System.out.println(this.student_selected.size() + "students selected");
        for (int i = 0; i < this.student_selected.size(); i++) {
            System.out.println("Name:" + student_selected.get(i).name);
            System.out.println("Name:" + student_selected.get(i).roll_no);
            System.out.println("Name:" + student_selected.get(i).Branch);
        }
    }

    public void update_role() {
        Scanner x = new Scanner(System.in);
        String newrole = x.nextLine();

        this.role_offering = newrole;
    }

    public void update_cg_req() {
        Scanner x = new Scanner(System.in);
        double newrole = x.nextDouble();

        this.cg_req = newrole;
    }

    public void update_package() {
        Scanner x = new Scanner(System.in);
        int newrole = x.nextInt();

        this.package_offered = newrole;
    }
}

class PlacementCell {
    static String stud_reg_opening;
    static String stud_reg_closing;
    static String comp_reg_opening;
    static String comp_reg_closing;
    static ArrayList<Student> reg_stud = new ArrayList<Student>();
    static ArrayList<Student> stud_having_offer = new ArrayList<Student>();
    static ArrayList<Student> blocked_stud = new ArrayList<Student>();
    static ArrayList<Company> comp_reg = new ArrayList<Company>();

    public static void student_registartion() {
        Scanner x = new Scanner(System.in);
        stud_reg_opening = x.nextLine();

        Scanner y = new Scanner(System.in);
        stud_reg_closing = x.nextLine();
    }

    public static void company_registartion() {
        Scanner x = new Scanner(System.in);
        comp_reg_opening = x.nextLine();

        Scanner y = new Scanner(System.in);
        comp_reg_closing = x.nextLine();
    }

    public static int num_of_student_registered() {
        return reg_stud.size();
    }

    public static int num_of_company_registered() {
        return comp_reg.size();
    }

    public static void num_of_student_unofferd_blocked_offered() {
        System.out.println("Offered: " + stud_having_offer.size());
        System.out.println("Blocked: " + blocked_stud.size());
        System.out.println("Un-offered: " + (reg_stud.size() - stud_having_offer.size() - blocked_stud.size()));
    }

    public static void get_student_details() {
        Scanner x = new Scanner(System.in);
        String name = x.nextLine();

        Scanner y = new Scanner(System.in);
        int roll = y.nextInt();

        for (int i = 0; i < reg_stud.size(); i++) {
            if (reg_stud.get(i).name == name & reg_stud.get(i).roll_no == roll) {
                System.out.println("Name:" + name);
                System.out.println("Roll num:" + roll);
                System.out.println("CGPA:" + reg_stud.get(i).CGPA);
                System.out.println("Branch:" + reg_stud.get(i).Branch);
                System.out.println("Status:" + reg_stud.get(i).Status);
            }
        }
    }

    public static void get_company_details() {
        Scanner x = new Scanner(System.in);
        String name = x.nextLine();

        for (int i = 0; i < comp_reg.size(); i++) {
            if (comp_reg.get(i).name == name) {
                System.out.println("Name:" + name);
                System.out.println("Role Offering:" + comp_reg.get(i).role_offering);
                System.out.println("CGPA required:" + comp_reg.get(i).cg_req);
                System.out.println("CTC offered:" + comp_reg.get(i).package_offered);
                for (int j = 0; j < comp_reg.get(i).student_selected.size(); j++) {
                    System.out.println(comp_reg.get(i).student_selected.get(j).name);
                }
            }
        }
    }

    public static int get_avg_package() {
        int total = 0;

        if (reg_stud.size() == 0) {
            System.out.println("No Studen Registered");
            return 0;
        }

        for (int i = 0; i < reg_stud.size(); i++) {
            total += reg_stud.get(i).current_CTC;
        }

        return total / reg_stud.size();
    }

    public static void get_company_process_results() {
        Scanner x = new Scanner(System.in);
        String name = x.nextLine();

        for (int i = 0; i < comp_reg.size(); i++) {
            if (comp_reg.get(i).name == name) {
                for (int j = 0; j < comp_reg.get(i).student_selected.size(); j++) {
                    System.out.println(comp_reg.get(i).student_selected.get(j).name);
                }
            }
        }
    }

}

public class AP_assignment1 {


    
    static ArrayList<Student> stud = new ArrayList<Student>();
    static ArrayList<Company> comp = new ArrayList<Company>();



    public static void enter_as_student(String name, int roll){
        int indice = -1;
        for(int i =0 ; i < stud.size() ; i++){
            if(stud.get(i).name == name && stud.get(i).roll_no == roll){
                indice = i;
            }
        }
        if(indice == -1){
            System.out.println("You are not registered");
            return;
        }
        Student stu = stud.get(indice);
        while(true){
            System.out.println("Welcome" + stu.name);
            System.out.println("1) Register For Placement Drive");
            System.out.println("2) Register For Company");
            System.out.println("3) Get All available companies");
            System.out.println("4) Get Current Status");
            System.out.println("5) Update CGPA");
            System.out.println("6) Accept offer");
            System.out.println("7) Reject offer");
            System.out.println("8) Back");


            System.out.println("Choose the index of operation you wantt to perform");
            Scanner fci = new Scanner(System.in);
            int opr = fci.nextInt();

            if(opr == 1){
                stu.registered();
            }
            else if(opr == 2){
                stu.register_for_company();
            }
            else if(opr == 3){
                stu.get_all_companies();
            }
            else if(opr == 4){
                stu.get_status();
            }
            else if(opr == 5){
                stu.update_cgpa();
            }
            else if(opr == 6){
                stu.accept();
            }
            else if(opr == 7){
                stu.reject();
            }
            else if(opr == 8){
                break;
            }
        }

    }

    public static void companyOpen(int index){
        Company com = comp.get(index);
        while(true){
            System.out.println("Welcome" + com.name);
            System.out.println("1.)Update Role");
            System.out.println("2.)Update Package");
            System.out.println("3.)Update CGPA criteria");
            System.out.println("4.)Register to institute Drive");
            System.out.println("5.)Back");

            System.out.println("Choose the index of operation you wantt to perform");
            Scanner fci = new Scanner(System.in);
            int opr = fci.nextInt();

            if(opr == 1){
                // update role
                com.update_role();
            }
            else if(opr == 2){
                // Update Package
                com.update_package();
            }
            else if(opr == 3){
                // Update CGPA criteria
                com.update_cg_req();
            }
            else if(opr == 4){
                // Register to insitute Drive
                com.register_to_institute_drive();
            }
            else{
                // Back
                break;
            }
        }
    }






    public static void main(String[] args) {

        while (true) {
            System.out.println("Welcome to fututebuilder");
            System.out.println("1.) Enter The Application");
            System.out.println("2.) Exit The Application");

            Scanner sc = new Scanner(System.in);
            int enter_exit = sc.nextInt();

            if (enter_exit == 1) {
                while (true) {
                    System.out.println("Choose the mode you want to enter in:");
                    System.out.println("1.) Enter as Student mode");
                    System.out.println("2.) Enter as Company mode");
                    System.out.println("3.) Enter as Placement cell mode");
                    System.out.println("4.) Return to main menu");

                    Scanner c = new Scanner(System.in);
                    int mode = c.nextInt();

                    if (mode == 3) {

                        // entered as a placement

                        while (true) {
                            System.out.println("Welcome to IIITD Placement Cell:");
                            System.out.println("1.) Open Student Registrations");
                            System.out.println("2.) Open Company Registrations");
                            System.out.println("3.) Get Number of Student Registrations");
                            System.out.println("4.) Get Number of Company Registrations");
                            System.out.println("5.) Get number of Offered/Unoffered/Blocked Students");
                            System.out.println("6.) Get Student Details");
                            System.out.println("7.) Get Company Details");
                            System.out.println("8.) Get Average Details");
                            System.out.println("9.) Get Company Process Results");
                            System.out.println("10.) Back");

                            Scanner kc = new Scanner(System.in);
                            int opm = kc.nextInt();

                            // yha 1 object bnega placement cell class ka on which all the functions in if
                            // else will be called

                            if (opm == 1) {
                                // open student regs
                                PlacementCell.student_registartion();
                            } else if (opm == 2) {
                                // open company regs
                                PlacementCell.company_registartion();
                            } else if (opm == 3) {
                                // get num of student regs
                                int ans = PlacementCell.num_of_student_registered();
                                System.out.println(ans);
                            } else if (opm == 4) {
                                // get num of company regs
                                int ans = PlacementCell.num_of_company_registered();
                                System.out.println(ans);
                            } else if (opm == 5) {
                                // Get number of Offered/Unoffered/Blocked Students
                                PlacementCell.num_of_student_unofferd_blocked_offered();
                            } else if (opm == 6) {
                                // get student details
                                PlacementCell.get_student_details();
                            } else if (opm == 7) {
                                // get company details
                                PlacementCell.get_company_details();
                            } else if (opm == 8) {
                                // get avg details
                                int ans = PlacementCell.get_avg_package();
                                System.out.println(ans);
                            } else if (opm == 9) {
                                // get company process resukts
                                PlacementCell.get_company_process_results();
                            } else {
                                break;
                            }
                        }
                    } 
                    else if (mode == 2) {

                        // Entered as a company
                        while (true) {
                            System.out.println("Choose the Company Query to perform:");
                            System.out.println("1.) Add company and details");
                            System.out.println("2.) Choose company ");
                            System.out.println("3.) Get available companies");
                            System.out.println("4.) Back");

                            Scanner kc = new Scanner(System.in);
                            int opm = kc.nextInt();

                            // yha pr company class ka object bnega jisme if else wale functions call honge

                            if (opm == 1) {
                                // add compnay details
                                String name;
                                String role_offering;
                                int package_offered;
                                double cg_req;
                                Scanner sci = new Scanner(System.in);
                                name = sci.nextLine();
                                role_offering = sci.nextLine();
                                Scanner sci1 = new Scanner(System.in);
                                package_offered = sci1.nextInt();
                                Scanner sci2 = new Scanner(System.in);
                                cg_req = sci2.nextDouble();
                                Company temp = new Company(name, role_offering, package_offered, cg_req);
                                comp.add(temp);

                                }
                            else if (opm == 2) {
                                // choose company
                                if (comp.size() == 0){
                                    System.out.println("No companies!!");
                                }
                                else{
                                    for (int i = 0; i< comp.size(); i++){
                                        System.out.println(comp.get(i).name);
                                    }
                                    System.out.println("Give the index of company you wanna access!");
                                    Scanner scii = new Scanner(System.in);
                                    int indice = scii.nextInt();
                                    
                                    companyOpen(indice);

                                }
                            }
                            else if (opm == 3) {
                                // get available companies
                                for(int i = 0; i< comp.size(); i++){
                                    System.out.println(comp.get(i).name);
                                }
                            }
                            else {
                                break;
                            }
                        }

                    } 
                    else if (mode == 1) {
                        // Entered as student

                        while (true) {
                            System.out.println("Choose the Student Query to perform:");
                            System.out.println("1.) Enter as Student( Give Student Name, and Roll no. )");
                            System.out.println("2.) Add Student ");
                            System.out.println("3.) Back");

                            Scanner kc = new Scanner(System.in);
                            int opm = kc.nextInt();

                            // yha pr student class ka object bnega jisme if else wale functions call honge

                            if (opm == 1) {
                                // enter as student
                                Scanner nam = new Scanner(System.in);
                                String name = nam.nextInt();

                                Scanner rolo = new Scanner(System.in);
                                int roll = rolo.nextInt();


                                enter_as_student(name, roll);


                            } 
                            else if (opm == 2) {
                                // add student
                                System.out.print("Give number of students: ");
                                int count;
                                Scanner a = new Scanner(System.in);
                                count = a.nextInt();
                                for (int i = 0 ; i < count; i++){
                                    String name;
                                    int roll;
                                    double cg;
                                    String branch;
                                    int ctc; 

                                    Scanner a_name = new Scanner(System.in);
                                    name = a_name.nextLine();

                                    Scanner a_roll = new Scanner(System.in);
                                    roll = a_name.nextInt();

                                    Scanner a_cg = new Scanner(System.in);
                                    cg = a_name.nextDouble();

                                    Scanner a_branch = new Scanner(System.in);
                                    branch = a_name.nextLine();

                                    Scanner a_ctc = new Scanner(System.in);
                                    ctc = a_name.nextInt();

                                    Student abc = new Student(name,roll,cg,branch,ctc);
                                    stud.add(abc);
                            } 
                        }
                            else{
                                break;
                            }
                        }
                    }
                    else {
                        break;
                    }    
                }
                    
                } 
            
            else {
                System.out.println("Thanks for using FututeBuilder");
                break;
            }
                
            } 
        }
    }
