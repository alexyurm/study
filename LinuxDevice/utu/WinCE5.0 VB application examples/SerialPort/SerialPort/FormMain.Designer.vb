<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Public Class FormMain
    Inherits System.Windows.Forms.Form

    'Form overrides dispose to clean up the component list.
    <System.Diagnostics.DebuggerNonUserCode()> _
    Protected Overrides Sub Dispose(ByVal disposing As Boolean)
        If disposing AndAlso components IsNot Nothing Then
            components.Dispose()
        End If
        MyBase.Dispose(disposing)
    End Sub

    'Required by the Windows Form Designer
    Private components As System.ComponentModel.IContainer

    'NOTE: The following procedure is required by the Windows Form Designer
    'It can be modified using the Windows Form Designer.  
    'Do not modify it using the code editor.
    <System.Diagnostics.DebuggerStepThrough()> _
    Private Sub InitializeComponent()
        Me.components = New System.ComponentModel.Container
        Me.SerialPort1 = New System.IO.Ports.SerialPort(Me.components)
        Me.ComboBoxCom = New System.Windows.Forms.ComboBox
        Me.ComboBoxBaudrate = New System.Windows.Forms.ComboBox
        Me.Label1 = New System.Windows.Forms.Label
        Me.Label2 = New System.Windows.Forms.Label
        Me.TextBoxReceived = New System.Windows.Forms.TextBox
        Me.Label6 = New System.Windows.Forms.Label
        Me.ButtonClearReceived = New System.Windows.Forms.Button
        Me.ButtonClearSending = New System.Windows.Forms.Button
        Me.Label7 = New System.Windows.Forms.Label
        Me.TextBoxSending = New System.Windows.Forms.TextBox
        Me.ButtonSend = New System.Windows.Forms.Button
        Me.Panel1 = New System.Windows.Forms.Panel
        Me.Panel2 = New System.Windows.Forms.Panel
        Me.Timer1 = New System.Windows.Forms.Timer
        Me.Panel1.SuspendLayout()
        Me.Panel2.SuspendLayout()
        Me.SuspendLayout()
        '
        'SerialPort1
        '
        Me.SerialPort1.BaudRate = 115200
        '
        'ComboBoxCom
        '
        Me.ComboBoxCom.Items.Add("COM1")
        Me.ComboBoxCom.Items.Add("COM2")
        Me.ComboBoxCom.Items.Add("COM3")
        Me.ComboBoxCom.Location = New System.Drawing.Point(84, 23)
        Me.ComboBoxCom.Name = "ComboBoxCom"
        Me.ComboBoxCom.Size = New System.Drawing.Size(100, 23)
        Me.ComboBoxCom.TabIndex = 0
        Me.ComboBoxCom.TabStop = False
        '
        'ComboBoxBaudrate
        '
        Me.ComboBoxBaudrate.Items.Add("9600")
        Me.ComboBoxBaudrate.Items.Add("115200")
        Me.ComboBoxBaudrate.Location = New System.Drawing.Point(84, 66)
        Me.ComboBoxBaudrate.Name = "ComboBoxBaudrate"
        Me.ComboBoxBaudrate.Size = New System.Drawing.Size(100, 23)
        Me.ComboBoxBaudrate.TabIndex = 1
        Me.ComboBoxBaudrate.TabStop = False
        '
        'Label1
        '
        Me.Label1.Location = New System.Drawing.Point(16, 26)
        Me.Label1.Name = "Label1"
        Me.Label1.Size = New System.Drawing.Size(62, 20)
        Me.Label1.Text = "COM"
        '
        'Label2
        '
        Me.Label2.Location = New System.Drawing.Point(16, 69)
        Me.Label2.Name = "Label2"
        Me.Label2.Size = New System.Drawing.Size(62, 20)
        Me.Label2.Text = "Baudrate"
        '
        'TextBoxReceived
        '
        Me.TextBoxReceived.Location = New System.Drawing.Point(11, 35)
        Me.TextBoxReceived.Multiline = True
        Me.TextBoxReceived.Name = "TextBoxReceived"
        Me.TextBoxReceived.Size = New System.Drawing.Size(150, 156)
        Me.TextBoxReceived.TabIndex = 12
        Me.TextBoxReceived.TabStop = False
        '
        'Label6
        '
        Me.Label6.Location = New System.Drawing.Point(11, 9)
        Me.Label6.Name = "Label6"
        Me.Label6.Size = New System.Drawing.Size(61, 20)
        Me.Label6.Text = "Received"
        '
        'ButtonClearReceived
        '
        Me.ButtonClearReceived.Location = New System.Drawing.Point(89, 9)
        Me.ButtonClearReceived.Name = "ButtonClearReceived"
        Me.ButtonClearReceived.Size = New System.Drawing.Size(72, 20)
        Me.ButtonClearReceived.TabIndex = 14
        Me.ButtonClearReceived.TabStop = False
        Me.ButtonClearReceived.Text = "Clear"
        '
        'ButtonClearSending
        '
        Me.ButtonClearSending.Location = New System.Drawing.Point(89, 73)
        Me.ButtonClearSending.Name = "ButtonClearSending"
        Me.ButtonClearSending.Size = New System.Drawing.Size(72, 20)
        Me.ButtonClearSending.TabIndex = 17
        Me.ButtonClearSending.TabStop = False
        Me.ButtonClearSending.Text = "Clear"
        '
        'Label7
        '
        Me.Label7.Location = New System.Drawing.Point(11, 11)
        Me.Label7.Name = "Label7"
        Me.Label7.Size = New System.Drawing.Size(61, 20)
        Me.Label7.Text = "Sending"
        '
        'TextBoxSending
        '
        Me.TextBoxSending.Location = New System.Drawing.Point(11, 34)
        Me.TextBoxSending.Name = "TextBoxSending"
        Me.TextBoxSending.Size = New System.Drawing.Size(150, 23)
        Me.TextBoxSending.TabIndex = 16
        Me.TextBoxSending.TabStop = False
        '
        'ButtonSend
        '
        Me.ButtonSend.Location = New System.Drawing.Point(11, 73)
        Me.ButtonSend.Name = "ButtonSend"
        Me.ButtonSend.Size = New System.Drawing.Size(72, 20)
        Me.ButtonSend.TabIndex = 19
        Me.ButtonSend.TabStop = False
        Me.ButtonSend.Text = "Send"
        '
        'Panel1
        '
        Me.Panel1.BackColor = System.Drawing.Color.LightGreen
        Me.Panel1.Controls.Add(Me.TextBoxReceived)
        Me.Panel1.Controls.Add(Me.Label6)
        Me.Panel1.Controls.Add(Me.ButtonClearReceived)
        Me.Panel1.Location = New System.Drawing.Point(200, 9)
        Me.Panel1.Name = "Panel1"
        Me.Panel1.Size = New System.Drawing.Size(177, 204)
        '
        'Panel2
        '
        Me.Panel2.BackColor = System.Drawing.Color.PaleTurquoise
        Me.Panel2.Controls.Add(Me.Label7)
        Me.Panel2.Controls.Add(Me.TextBoxSending)
        Me.Panel2.Controls.Add(Me.ButtonSend)
        Me.Panel2.Controls.Add(Me.ButtonClearSending)
        Me.Panel2.Location = New System.Drawing.Point(16, 107)
        Me.Panel2.Name = "Panel2"
        Me.Panel2.Size = New System.Drawing.Size(177, 106)
        '
        'Timer1
        '
        Me.Timer1.Enabled = True
        '
        'FormMain
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(96.0!, 96.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Dpi
        Me.AutoScroll = True
        Me.ClientSize = New System.Drawing.Size(388, 225)
        Me.Controls.Add(Me.Panel2)
        Me.Controls.Add(Me.Panel1)
        Me.Controls.Add(Me.Label2)
        Me.Controls.Add(Me.Label1)
        Me.Controls.Add(Me.ComboBoxBaudrate)
        Me.Controls.Add(Me.ComboBoxCom)
        Me.Name = "FormMain"
        Me.Text = "Serial Port"
        Me.Panel1.ResumeLayout(False)
        Me.Panel2.ResumeLayout(False)
        Me.ResumeLayout(False)

    End Sub
    Friend WithEvents SerialPort1 As System.IO.Ports.SerialPort
    Friend WithEvents ComboBoxCom As System.Windows.Forms.ComboBox
    Friend WithEvents ComboBoxBaudrate As System.Windows.Forms.ComboBox
    Friend WithEvents Label1 As System.Windows.Forms.Label
    Friend WithEvents Label2 As System.Windows.Forms.Label
    Friend WithEvents TextBoxReceived As System.Windows.Forms.TextBox
    Friend WithEvents Label6 As System.Windows.Forms.Label
    Friend WithEvents ButtonClearReceived As System.Windows.Forms.Button
    Friend WithEvents ButtonClearSending As System.Windows.Forms.Button
    Friend WithEvents Label7 As System.Windows.Forms.Label
    Friend WithEvents TextBoxSending As System.Windows.Forms.TextBox
    Friend WithEvents ButtonSend As System.Windows.Forms.Button
    Friend WithEvents Panel1 As System.Windows.Forms.Panel
    Friend WithEvents Panel2 As System.Windows.Forms.Panel
    Friend WithEvents Timer1 As System.Windows.Forms.Timer

End Class
