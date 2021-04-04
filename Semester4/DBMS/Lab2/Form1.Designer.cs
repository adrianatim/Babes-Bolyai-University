
namespace Baze1
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.dgvChild = new System.Windows.Forms.DataGridView();
            this.dgvParent = new System.Windows.Forms.DataGridView();
            this.deleteButton = new System.Windows.Forms.Button();
            this.updateButton = new System.Windows.Forms.Button();
            this.addButton = new System.Windows.Forms.Button();
            this.connectButton = new System.Windows.Forms.Button();
            this.flowLayoutPanel1 = new System.Windows.Forms.FlowLayoutPanel();
            ((System.ComponentModel.ISupportInitialize)(this.dgvChild)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dgvParent)).BeginInit();
            this.SuspendLayout();
            // 
            // dgvChild
            // 
            this.dgvChild.AllowUserToAddRows = false;
            this.dgvChild.AllowUserToDeleteRows = false;
            this.dgvChild.BackgroundColor = System.Drawing.Color.LavenderBlush;
            this.dgvChild.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dgvChild.Location = new System.Drawing.Point(31, 23);
            this.dgvChild.Name = "dgvChild";
            this.dgvChild.Size = new System.Drawing.Size(462, 226);
            this.dgvChild.TabIndex = 0;
            // 
            // dgvParent
            // 
            this.dgvParent.AllowUserToAddRows = false;
            this.dgvParent.AllowUserToDeleteRows = false;
            this.dgvParent.BackgroundColor = System.Drawing.Color.LavenderBlush;
            this.dgvParent.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dgvParent.GridColor = System.Drawing.Color.DarkGray;
            this.dgvParent.Location = new System.Drawing.Point(695, 23);
            this.dgvParent.Name = "dgvParent";
            this.dgvParent.Size = new System.Drawing.Size(465, 226);
            this.dgvParent.TabIndex = 1;
            // 
            // deleteButton
            // 
            this.deleteButton.BackColor = System.Drawing.Color.Pink;
            this.deleteButton.Location = new System.Drawing.Point(67, 413);
            this.deleteButton.Name = "deleteButton";
            this.deleteButton.Size = new System.Drawing.Size(95, 48);
            this.deleteButton.TabIndex = 2;
            this.deleteButton.Text = "DELETE";
            this.deleteButton.UseVisualStyleBackColor = false;
            this.deleteButton.Click += new System.EventHandler(this.deleteButton_Click);
            // 
            // updateButton
            // 
            this.updateButton.BackColor = System.Drawing.Color.Pink;
            this.updateButton.Location = new System.Drawing.Point(67, 340);
            this.updateButton.Name = "updateButton";
            this.updateButton.Size = new System.Drawing.Size(95, 51);
            this.updateButton.TabIndex = 3;
            this.updateButton.Text = "UPDATE";
            this.updateButton.UseVisualStyleBackColor = false;
            this.updateButton.Click += new System.EventHandler(this.updateButton_Click);
            // 
            // addButton
            // 
            this.addButton.BackColor = System.Drawing.Color.Pink;
            this.addButton.Location = new System.Drawing.Point(67, 269);
            this.addButton.Name = "addButton";
            this.addButton.Size = new System.Drawing.Size(95, 50);
            this.addButton.TabIndex = 4;
            this.addButton.Text = "ADD";
            this.addButton.UseVisualStyleBackColor = false;
            this.addButton.Click += new System.EventHandler(this.addButton_Click);
            // 
            // connectButton
            // 
            this.connectButton.BackColor = System.Drawing.Color.DeepPink;
            this.connectButton.Location = new System.Drawing.Point(525, 100);
            this.connectButton.Name = "connectButton";
            this.connectButton.Size = new System.Drawing.Size(139, 70);
            this.connectButton.TabIndex = 6;
            this.connectButton.Text = "CONNECT";
            this.connectButton.UseVisualStyleBackColor = false;
            this.connectButton.Click += new System.EventHandler(this.connectButton_Click);
            // 
            // flowLayoutPanel1
            // 
            this.flowLayoutPanel1.Location = new System.Drawing.Point(503, 269);
            this.flowLayoutPanel1.Name = "flowLayoutPanel1";
            this.flowLayoutPanel1.Size = new System.Drawing.Size(193, 266);
            this.flowLayoutPanel1.TabIndex = 7;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(230)))), ((int)(((byte)(230)))));
            this.BackgroundImageLayout = System.Windows.Forms.ImageLayout.None;
            this.ClientSize = new System.Drawing.Size(1172, 554);
            this.Controls.Add(this.flowLayoutPanel1);
            this.Controls.Add(this.connectButton);
            this.Controls.Add(this.addButton);
            this.Controls.Add(this.updateButton);
            this.Controls.Add(this.deleteButton);
            this.Controls.Add(this.dgvParent);
            this.Controls.Add(this.dgvChild);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.Fixed3D;
            this.Name = "Form1";
            this.Text = "Form1";
            ((System.ComponentModel.ISupportInitialize)(this.dgvChild)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dgvParent)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.DataGridView dgvChild;
        private System.Windows.Forms.DataGridView dgvParent;
        private System.Windows.Forms.Button deleteButton;
        private System.Windows.Forms.Button updateButton;
        private System.Windows.Forms.Button addButton;
        private System.Windows.Forms.Button connectButton;
        private System.Windows.Forms.FlowLayoutPanel flowLayoutPanel1;
    }
}

