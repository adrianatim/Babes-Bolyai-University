using System;
using System.Data;
using System.Windows.Forms;
using System.Data.SqlClient;
using System.Configuration;
using System.Collections.Specialized;
using System.Collections.Generic;

namespace Baze1
{
    public partial class Form1 : Form
    {
        SqlConnection connection;
        DataSet ds;
        SqlDataAdapter daChild, daParent;
        BindingSource bsChild, bsParent;
        string parentTableName = ConfigurationManager.AppSettings.Get("ParentTableName");
        string childTableName = ConfigurationManager.AppSettings.Get("ChildTableName");
        string columnNamesInsertParameters = ConfigurationManager.AppSettings.Get("ColumnNamesInsertParameters");
        List<string> columnNames = new List<string>(ConfigurationManager.AppSettings.Get("ChildLabelNames").Split(','));
        List<string> paramsNames = new List<string>(ConfigurationManager.AppSettings.Get("ColumnNamesInsertParameters").Split(','));
        int noOfColumns = Convert.ToInt32(ConfigurationManager.AppSettings.Get("ChildNoOfColumns"));
        private TextBox[] textBoxes;
        private Label[] labels;


        public Form1()
        {
            InitializeComponent();
            PopulatePanel();
        }

        private void PopulatePanel()
        {
            textBoxes = new TextBox[noOfColumns];
            labels = new Label[noOfColumns];

            for (int i = 0; i < noOfColumns; i++)
            {
                textBoxes[i] = new TextBox();
                textBoxes[i].Text = "";
                labels[i] = new Label();
                labels[i].Text = columnNames[i];
            }

            for (int i = 0; i < noOfColumns; i++)
            {
                flowLayoutPanel1.Controls.Add(textBoxes[i]);
                flowLayoutPanel1.Controls.Add(labels[i]);
            }
        }

        private void connectButton_Click(object sender, EventArgs e)
        {
            connection = new SqlConnection(GetConnectionString());
            initialTable();
        }

        private void addButton_Click(object sender, EventArgs e)
        {
            var command = new SqlCommand("INSERT INTO " + childTableName + " VALUES ( " + columnNamesInsertParameters + " ) ", connection);
            connection.Open();
            try
            {
                for(int i=0; i< noOfColumns; i++)
                {
                    command.Parameters.AddWithValue(paramsNames[i], textBoxes[i].Text);
                }

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
            var command = new SqlCommand(ConfigurationManager.AppSettings.Get("UpdateChild"), connection);

            connection.Open();
            try
            {
                for (int i = 0; i < noOfColumns; i++)
                {
                    command.Parameters.AddWithValue(paramsNames[i], textBoxes[i].Text);
                }

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
            var command = new SqlCommand(ConfigurationManager.AppSettings.Get("DeleteChild"), connection);
            connection.Open();
            try
            {
                command.Parameters.AddWithValue(paramsNames[0], textBoxes[0].Text);
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
            daChild = new SqlDataAdapter(ConfigurationManager.AppSettings.Get("SelectChild"), connection);
            daParent = new SqlDataAdapter(ConfigurationManager.AppSettings.Get("SelectParent"), connection);

            SqlCommandBuilder commandBuilder = new SqlCommandBuilder(daParent);

            daChild.Fill(ds, childTableName);
            daParent.Fill(ds, parentTableName);

            string parentId = ConfigurationManager.AppSettings.Get("ParentId");
            DataRelation dr = new DataRelation("FK_Table1_Table2", ds.Tables[parentTableName].Columns[parentId],
                ds.Tables[childTableName].Columns[parentId]);
            ds.Relations.Add(dr);

            bsChild = new BindingSource();
            bsParent = new BindingSource();

            bsParent.DataSource = ds;
            bsParent.DataMember = parentTableName;

            bsChild.DataSource = bsParent;
            bsChild.DataMember = "FK_Table1_Table2";

            dgvChild.DataSource = bsChild;
            dgvParent.DataSource = bsParent;
        }

        private void clearTextBoxes()
        {
            for(int i=0; i<noOfColumns; i++)
            {
                textBoxes[i].Text = "";
            }
        }

        private static String GetConnectionString()
        {
            return "Data Source=DESKTOP-PU9UT93\\SQLEXPRESS;" +
                   "Initial Catalog=DrivingSchoolDB;" +
                   "Integrated Security=SSPI";
        }
    }
}
