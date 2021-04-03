using System;
using System.Data;
using System.Windows.Forms;
using System.Data.SqlClient;


namespace Baze1
{
    public partial class Form1 : Form
    {
        SqlConnection connection;
        DataSet ds;
        SqlCommandBuilder commandBuilder;
        SqlDataAdapter daDualControlCar, daDrivingInstructor;
        BindingSource bsDualControlCar, bsDrivingInstructor;

        public Form1()
        {
            InitializeComponent();
        }

        private void connectButton_Click(object sender, EventArgs e)
        {
            connection = new SqlConnection(@"Data Source=DESKTOP-PU9UT93\SQLEXPRESS;Initial Catalog=DrivingSchoolDB;Integrated Security=SSPI");
            initialTable();
        }

        private void addButton_Click(object sender, EventArgs e)
        {
            var command = new SqlCommand("INSERT INTO dual_control_car VALUES (@dccId, @dccBrand, @dccFuel, @diId, @carTypeCategory)", connection);
            connection.Open();
            try
            {
                command.Parameters.AddWithValue("@dccId", textBox1.Text);
                command.Parameters.AddWithValue("@dccBrand", textBox2.Text);
                command.Parameters.AddWithValue("@dccFuel", textBox3.Text);
                command.Parameters.AddWithValue("@diId", textBox4.Text);
                command.Parameters.AddWithValue("@carTypeCategory", textBox5.Text);

                command.ExecuteNonQuery();

                clearTextBoxes();
                initialTable();

            }
            catch (Exception exc)
            {
                MessageBox.Show("The following error occured:" + exc.ToString());
            }
            connection.Close();
        }

        private void updateButton_Click(object sender, EventArgs e)
        {
            var command = new SqlCommand("UPDATE dual_control_car SET dccBrand=@dccBrand, dccFuel=@dccFuel, diId=@diId, carTypeCategory=@carTypeCategory WHERE dccId = @dccId", connection);

            connection.Open();
            try
            {
                command.Parameters.AddWithValue("@dccId", textBox1.Text);
                command.Parameters.AddWithValue("@dccBrand", textBox2.Text);
                command.Parameters.AddWithValue("@dccFuel", textBox3.Text);
                command.Parameters.AddWithValue("@diId", textBox4.Text);
                command.Parameters.AddWithValue("@carTypeCategory", textBox5.Text);

                command.ExecuteNonQuery();

                clearTextBoxes();
                initialTable();

            }
            catch (Exception exc)
            {
                MessageBox.Show("The following error occured:" + exc.ToString());
            }
            connection.Close();
        }

        private void deleteButton_Click(object sender, EventArgs e)
        {
            var command = new SqlCommand("DELETE FROM dual_control_car WHERE dccId = @dccId", connection);
            connection.Open();
            try
            {
                command.Parameters.AddWithValue("@dccId", textBox1.Text);
                command.ExecuteNonQuery();

                clearTextBoxes();
                initialTable();
            }
            catch (Exception exc)
            {
                MessageBox.Show("The following error occured:" + exc.ToString());
            }
            connection.Close();
        }

        private void initialTable()
        {
            ds = new DataSet();
            daDualControlCar = new SqlDataAdapter("select * from dual_control_car", connection);
            daDrivingInstructor = new SqlDataAdapter("select * from driving_instructor", connection);

            commandBuilder = new SqlCommandBuilder(daDrivingInstructor);

            daDualControlCar.Fill(ds, "dual_control_car");
            daDrivingInstructor.Fill(ds, "driving_instructor");

            DataRelation dr = new DataRelation("FK_DI_DCC", ds.Tables["driving_instructor"].Columns["diId"],
                ds.Tables["dual_control_car"].Columns["diId"]);
            ds.Relations.Add(dr);

            bsDualControlCar = new BindingSource();
            bsDrivingInstructor = new BindingSource();

            bsDrivingInstructor.DataSource = ds;
            bsDrivingInstructor.DataMember = "driving_instructor";

            bsDualControlCar.DataSource = bsDrivingInstructor;
            bsDualControlCar.DataMember = "FK_DI_DCC";

            dgvDualControlCar.DataSource = bsDualControlCar;
            dgvDrivingInstructor.DataSource = bsDrivingInstructor;
        }

        private void clearTextBoxes()
        {
            textBox1.Text = "";
            textBox2.Text = "";
            textBox3.Text = "";
            textBox4.Text = "";
            textBox5.Text = "";
        }
    }
}
