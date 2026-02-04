package test;

import com.hogwarts.dao.AlunoDAO;
import com.hogwarts.dao.DashboardDAO;

public class Teste {
    public static void main(String[] args) {
        DashboardDAO d = new DashboardDAO();

        System.out.println(d.gerarDashboard());
    }
}
