/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JFrame;

import DatabaseConnection.ConnectionProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ABC
 */
public class IssueBook extends javax.swing.JFrame {

    /**
     * Creates new form IssueBook
     */
    public IssueBook() {
        initComponents();
    }
    //to fetch the book details from the database and display it to book details panel
    public void getBookDetails()
    {
        int bookId=Integer.parseInt(txt_bookId.getText());
        try
        {
            Connection con=ConnectionProvider.getCon();
            PreparedStatement ps=con.prepareStatement("select * from book_details where book_id=?");
            ps.setInt(1, bookId);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
                lbl_bookId.setText(rs.getString("book_id"));
                lbl_BookName.setText(rs.getString("book_name"));
                lbl_author.setText(rs.getString("author"));
                lbl_quantity.setText(rs.getString("quantity"));
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Invalid! Book ID");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }    
       
        public void getStudentDetails()
    {
        int studentId=Integer.parseInt(txt_studentId.getText());
        try
        {
            Connection con=ConnectionProvider.getCon();
            PreparedStatement ps=con.prepareStatement("select * from student_details where studentid=?");
            ps.setInt(1, studentId);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
                lbl_studentId.setText(rs.getString("studentid"));
                lbl_studentName.setText(rs.getString("name"));
                lbl_course.setText(rs.getString("course"));
                lbl_branch.setText(rs.getString("branch"));
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Invalid! Student ID");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
        
      //insert issue book details to database
        public boolean issueBook()
        {
            boolean isIssued=false;
            int bookId=Integer.parseInt(txt_bookId.getText());
            int studentId=Integer.parseInt(txt_studentId.getText());
            String bookName=lbl_BookName.getText();
            String studentName=lbl_studentName.getText();
            
            Date uIssueueDate=date_issueDate.getDatoFecha();
            Date uDueDate=date_dueDate.getDatoFecha();
            
            Long l1=uIssueueDate.getTime();
            Long l2=uDueDate.getTime();
            
            java.sql.Date sIssueDate=new java.sql.Date(l1);
            java.sql.Date sDueDate=new java.sql.Date(l2);
            
            try
            {
                 Connection con=ConnectionProvider.getCon();
                 String sql="insert into issue_book_details(book_id,book_name,student_id,student_name,"
                         +"issue_date,due_date,status) values(?,?,?,?,?,?,?)";
                 PreparedStatement ps=con.prepareStatement(sql);
                 ps.setInt(1,bookId);
                 ps.setString(2,bookName);
                 ps.setInt(3,studentId);
                 ps.setString(4,studentName);   
                 ps.setDate(5,sIssueDate);
                 ps.setDate(6,sDueDate);
                 ps.setString(7,"pending");
                 
                 int rowCount=ps.executeUpdate();
                 if(rowCount>0)
                 {
                     isIssued=true;
                 }
                 else
                 {
                     isIssued=false;
                 }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
//                JOptionPane.showMessageDialog(this, "please fill correct details!");
            }
            return isIssued;
        }
        
        //update count of quntity
        public void UpdateCount()
        {
            int bookId=Integer.parseInt(txt_bookId.getText());
            try
            {
            Connection con=ConnectionProvider.getCon();
            PreparedStatement ps=con.prepareStatement("update book_details set quantity=quantity-1 where book_id=?");
            ps.setInt(1,bookId);
            int rowCount=ps.executeUpdate();
            if(rowCount>0)
            {
                JOptionPane.showMessageDialog(this,"Book Count update succesfully!");
                int initialCount=Integer.parseInt(lbl_quantity.getText());
                lbl_quantity.setText(Integer.toString(initialCount-1));
            }
            else
            {
                JOptionPane.showMessageDialog(this,"Can't update Book count");
            }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
        
        //clear fields
        public void textFields()
        {
            lbl_BookName.setText("");
            lbl_bookId.setText("");
            lbl_studentName.setText("");
            lbl_author.setText("");
            lbl_branch.setText("");
            lbl_course.setText("");
            lbl_quantity.setText("");
            lbl_studentId.setText("");
        }
        
        // Book Already issued
        public boolean AlreadyIssue()
        {
            boolean isAlreadyIssued=false;
            int bookId=Integer.parseInt(txt_bookId.getText());
            int studentId=Integer.parseInt(txt_studentId.getText());
            try{
                 Connection con=ConnectionProvider.getCon();
            PreparedStatement ps=con.prepareStatement("select * from issue_book_details where book_id=? and student_id=? and status=?");
            ps.setInt(1, bookId);
            ps.setInt(2,studentId);
            ps.setString(3,"pending");
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
                isAlreadyIssued=true;
            }
            else
            {
                isAlreadyIssued=false;
            }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
            return isAlreadyIssued;
        }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbl_branch = new javax.swing.JLabel();
        lbl_studentId = new javax.swing.JLabel();
        lbl_studentName = new javax.swing.JLabel();
        lbl_course = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lbl_quantity = new javax.swing.JLabel();
        lbl_bookId = new javax.swing.JLabel();
        lbl_BookName = new javax.swing.JLabel();
        lbl_author = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txt_bookId = new app.bolivia.swing.JCTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_studentId = new app.bolivia.swing.JCTextField();
        jLabel10 = new javax.swing.JLabel();
        date_issueDate = new rojeru_san.componentes.RSDateChooser();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        date_dueDate = new rojeru_san.componentes.RSDateChooser();
        rSMaterialButtonCircle1 = new rojerusan.RSMaterialButtonCircle();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Vrinda", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Branch :");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 460, -1, -1));

        jLabel2.setFont(new java.awt.Font("Vrinda", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Sudent ID :");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 340, 100, -1));

        jLabel3.setFont(new java.awt.Font("Vrinda", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Student Name :");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 130, -1));

        jLabel5.setFont(new java.awt.Font("Vrinda", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Course :");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 420, 70, -1));

        lbl_branch.setFont(new java.awt.Font("Vrinda", 1, 20)); // NOI18N
        lbl_branch.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lbl_branch, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 460, 180, 30));

        lbl_studentId.setFont(new java.awt.Font("Vrinda", 1, 20)); // NOI18N
        lbl_studentId.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lbl_studentId, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 340, 180, 30));

        lbl_studentName.setFont(new java.awt.Font("Vrinda", 1, 20)); // NOI18N
        lbl_studentName.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lbl_studentName, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 380, 180, 30));

        lbl_course.setFont(new java.awt.Font("Vrinda", 1, 20)); // NOI18N
        lbl_course.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lbl_course, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 420, 180, 30));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Student_Registration_100px_2.png"))); // NOI18N
        jLabel17.setText(" Student Details");
        jLabel17.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 320, 110));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 380, 668));

        jPanel3.setBackground(java.awt.Color.red);
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Literature_100px_1.png"))); // NOI18N
        jLabel13.setText("  Book Details");
        jLabel13.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 300, 110));

        jLabel4.setFont(new java.awt.Font("Vrinda", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Quantity :");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 460, -1, -1));

        jLabel14.setFont(new java.awt.Font("Vrinda", 1, 20)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Book ID :");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, -1, -1));

        jLabel15.setFont(new java.awt.Font("Vrinda", 1, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Book Name :");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, -1, -1));

        jLabel16.setFont(new java.awt.Font("Vrinda", 1, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Author :");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 420, 70, -1));

        lbl_quantity.setFont(new java.awt.Font("Vrinda", 1, 20)); // NOI18N
        lbl_quantity.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(lbl_quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 460, 180, 30));

        lbl_bookId.setFont(new java.awt.Font("Vrinda", 1, 20)); // NOI18N
        lbl_bookId.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(lbl_bookId, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 340, 180, 30));

        lbl_BookName.setFont(new java.awt.Font("Vrinda", 1, 20)); // NOI18N
        lbl_BookName.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(lbl_BookName, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 380, 180, 30));

        lbl_author.setFont(new java.awt.Font("Vrinda", 1, 20)); // NOI18N
        lbl_author.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(lbl_author, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 420, 180, 30));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 25)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Rewind_48px.png"))); // NOI18N
        jLabel20.setText("Back");
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 668));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel11.setForeground(java.awt.Color.red);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Books_52px_1.png"))); // NOI18N
        jLabel11.setText("  Issue Book");
        jLabel11.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, java.awt.Color.red));
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 100, 250, 110));

        txt_bookId.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, java.awt.Color.red));
        txt_bookId.setForeground(java.awt.Color.red);
        txt_bookId.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_bookId.setPlaceholder("Enter Book Id...");
        txt_bookId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_bookIdFocusLost(evt);
            }
        });
        txt_bookId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bookIdActionPerformed(evt);
            }
        });
        jPanel1.add(txt_bookId, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 290, 240, -1));

        jLabel9.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel9.setForeground(java.awt.Color.red);
        jLabel9.setText("Book Id :");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 290, 110, 30));

        txt_studentId.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, java.awt.Color.red));
        txt_studentId.setForeground(java.awt.Color.red);
        txt_studentId.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_studentId.setPlaceholder("Enter Student Id...");
        txt_studentId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_studentIdFocusLost(evt);
            }
        });
        txt_studentId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_studentIdActionPerformed(evt);
            }
        });
        jPanel1.add(txt_studentId, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 350, 240, -1));

        jLabel10.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel10.setForeground(java.awt.Color.red);
        jLabel10.setText("Issue Date :");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 410, 130, 40));

        date_issueDate.setColorBackground(java.awt.Color.red);
        date_issueDate.setColorForeground(java.awt.Color.red);
        date_issueDate.setPlaceholder("Select Issue Date");
        jPanel1.add(date_issueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 410, -1, -1));

        jLabel18.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel18.setForeground(java.awt.Color.red);
        jLabel18.setText("Student Id :");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 350, 130, 30));

        jLabel19.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel19.setForeground(java.awt.Color.red);
        jLabel19.setText("Due Date :");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 470, 120, 40));

        date_dueDate.setColorBackground(java.awt.Color.red);
        date_dueDate.setColorForeground(java.awt.Color.red);
        date_dueDate.setPlaceholder("Select Due Date");
        jPanel1.add(date_dueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 470, -1, -1));

        rSMaterialButtonCircle1.setBackground(java.awt.Color.red);
        rSMaterialButtonCircle1.setText("ISSUE BOOK");
        rSMaterialButtonCircle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle1ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 530, 150, 60));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 25)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/red-x-mark-transparent-background-3.png"))); // NOI18N
        jLabel12.setText("Close");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 0, 100, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1270, 670));

        setSize(new java.awt.Dimension(1266, 668));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel12MouseClicked

    private void txt_bookIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookIdFocusLost
       if(!txt_bookId.getText().equals(""))
        getBookDetails();
    }//GEN-LAST:event_txt_bookIdFocusLost

    private void txt_bookIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bookIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bookIdActionPerformed

    private void txt_studentIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_studentIdFocusLost
         if(!txt_studentId.getText().equals(""))
        getStudentDetails();
    }//GEN-LAST:event_txt_studentIdFocusLost

    private void txt_studentIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_studentIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_studentIdActionPerformed

    private void rSMaterialButtonCircle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle1ActionPerformed
       
        if(lbl_quantity.getText().equals("0"))
        {
            JOptionPane.showMessageDialog(this, "Book is not Available");
        }
        else
        {
            if(AlreadyIssue()==false)
            {
                if(issueBook()==true)
                {
                    JOptionPane.showMessageDialog(this, "Book Issued Successfully!");
                    UpdateCount();
                  /*try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(IssueBook.class.getName()).log(Level.SEVERE, null, ex);
                    }*/
                    textFields();
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Cant't Issue the Book!");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(rootPane, "This Student Already Has This Book!");
            }
    }
 
    }//GEN-LAST:event_rSMaterialButtonCircle1ActionPerformed
    
    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
          HomePage homepage=new HomePage();
        homepage.setVisible(true);
        dispose(); 
    }//GEN-LAST:event_jLabel20MouseClicked

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new IssueBook().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.componentes.RSDateChooser date_dueDate;
    private rojeru_san.componentes.RSDateChooser date_issueDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lbl_BookName;
    private javax.swing.JLabel lbl_author;
    private javax.swing.JLabel lbl_bookId;
    private javax.swing.JLabel lbl_branch;
    private javax.swing.JLabel lbl_course;
    private javax.swing.JLabel lbl_quantity;
    private javax.swing.JLabel lbl_studentId;
    private javax.swing.JLabel lbl_studentName;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle1;
    private app.bolivia.swing.JCTextField txt_bookId;
    private app.bolivia.swing.JCTextField txt_studentId;
    // End of variables declaration//GEN-END:variables
}
