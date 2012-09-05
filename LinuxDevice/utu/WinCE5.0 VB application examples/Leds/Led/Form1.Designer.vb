<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Public Class Form1
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
        Me.ButtonLedOn1 = New System.Windows.Forms.Button
        Me.ButtonLedOn2 = New System.Windows.Forms.Button
        Me.ButtonLedOn3 = New System.Windows.Forms.Button
        Me.ButtonLedOn4 = New System.Windows.Forms.Button
        Me.ButtonLedOff1 = New System.Windows.Forms.Button
        Me.ButtonLedOff2 = New System.Windows.Forms.Button
        Me.ButtonLedOff3 = New System.Windows.Forms.Button
        Me.ButtonLedOff4 = New System.Windows.Forms.Button
        Me.SuspendLayout()
        '
        'ButtonLedOn1
        '
        Me.ButtonLedOn1.Font = New System.Drawing.Font("Arial", 14.0!, System.Drawing.FontStyle.Regular)
        Me.ButtonLedOn1.Location = New System.Drawing.Point(36, 27)
        Me.ButtonLedOn1.Name = "ButtonLedOn1"
        Me.ButtonLedOn1.Size = New System.Drawing.Size(104, 31)
        Me.ButtonLedOn1.TabIndex = 0
        Me.ButtonLedOn1.Text = "On Led1"
        '
        'ButtonLedOn2
        '
        Me.ButtonLedOn2.Font = New System.Drawing.Font("Arial", 14.0!, System.Drawing.FontStyle.Regular)
        Me.ButtonLedOn2.Location = New System.Drawing.Point(36, 64)
        Me.ButtonLedOn2.Name = "ButtonLedOn2"
        Me.ButtonLedOn2.Size = New System.Drawing.Size(104, 31)
        Me.ButtonLedOn2.TabIndex = 1
        Me.ButtonLedOn2.Text = "On Led2"
        '
        'ButtonLedOn3
        '
        Me.ButtonLedOn3.Font = New System.Drawing.Font("Arial", 14.0!, System.Drawing.FontStyle.Regular)
        Me.ButtonLedOn3.Location = New System.Drawing.Point(36, 101)
        Me.ButtonLedOn3.Name = "ButtonLedOn3"
        Me.ButtonLedOn3.Size = New System.Drawing.Size(104, 31)
        Me.ButtonLedOn3.TabIndex = 2
        Me.ButtonLedOn3.Text = "On Led3"
        '
        'ButtonLedOn4
        '
        Me.ButtonLedOn4.Font = New System.Drawing.Font("Arial", 14.0!, System.Drawing.FontStyle.Regular)
        Me.ButtonLedOn4.Location = New System.Drawing.Point(36, 138)
        Me.ButtonLedOn4.Name = "ButtonLedOn4"
        Me.ButtonLedOn4.Size = New System.Drawing.Size(104, 31)
        Me.ButtonLedOn4.TabIndex = 3
        Me.ButtonLedOn4.Text = "On Led4"
        '
        'ButtonLedOff1
        '
        Me.ButtonLedOff1.Font = New System.Drawing.Font("Arial", 14.0!, System.Drawing.FontStyle.Regular)
        Me.ButtonLedOff1.Location = New System.Drawing.Point(193, 27)
        Me.ButtonLedOff1.Name = "ButtonLedOff1"
        Me.ButtonLedOff1.Size = New System.Drawing.Size(104, 31)
        Me.ButtonLedOff1.TabIndex = 4
        Me.ButtonLedOff1.Text = "Off Led1"
        '
        'ButtonLedOff2
        '
        Me.ButtonLedOff2.Font = New System.Drawing.Font("Arial", 14.0!, System.Drawing.FontStyle.Regular)
        Me.ButtonLedOff2.Location = New System.Drawing.Point(193, 64)
        Me.ButtonLedOff2.Name = "ButtonLedOff2"
        Me.ButtonLedOff2.Size = New System.Drawing.Size(104, 31)
        Me.ButtonLedOff2.TabIndex = 5
        Me.ButtonLedOff2.Text = "Off Led2"
        '
        'ButtonLedOff3
        '
        Me.ButtonLedOff3.Font = New System.Drawing.Font("Arial", 14.0!, System.Drawing.FontStyle.Regular)
        Me.ButtonLedOff3.Location = New System.Drawing.Point(193, 101)
        Me.ButtonLedOff3.Name = "ButtonLedOff3"
        Me.ButtonLedOff3.Size = New System.Drawing.Size(104, 31)
        Me.ButtonLedOff3.TabIndex = 6
        Me.ButtonLedOff3.Text = "Off Led3"
        '
        'ButtonLedOff4
        '
        Me.ButtonLedOff4.Font = New System.Drawing.Font("Arial", 14.0!, System.Drawing.FontStyle.Regular)
        Me.ButtonLedOff4.Location = New System.Drawing.Point(193, 138)
        Me.ButtonLedOff4.Name = "ButtonLedOff4"
        Me.ButtonLedOff4.Size = New System.Drawing.Size(104, 31)
        Me.ButtonLedOff4.TabIndex = 7
        Me.ButtonLedOff4.Text = "Off Led4"
        '
        'Form1
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(96.0!, 96.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Dpi
        Me.AutoScroll = True
        Me.ClientSize = New System.Drawing.Size(359, 203)
        Me.Controls.Add(Me.ButtonLedOff4)
        Me.Controls.Add(Me.ButtonLedOff3)
        Me.Controls.Add(Me.ButtonLedOff2)
        Me.Controls.Add(Me.ButtonLedOff1)
        Me.Controls.Add(Me.ButtonLedOn4)
        Me.Controls.Add(Me.ButtonLedOn3)
        Me.Controls.Add(Me.ButtonLedOn2)
        Me.Controls.Add(Me.ButtonLedOn1)
        Me.Name = "Form1"
        Me.Text = "Leds"
        Me.ResumeLayout(False)

    End Sub
    Friend WithEvents ButtonLedOn1 As System.Windows.Forms.Button
    Friend WithEvents ButtonLedOn2 As System.Windows.Forms.Button
    Friend WithEvents ButtonLedOn3 As System.Windows.Forms.Button
    Friend WithEvents ButtonLedOn4 As System.Windows.Forms.Button
    Friend WithEvents ButtonLedOff1 As System.Windows.Forms.Button
    Friend WithEvents ButtonLedOff2 As System.Windows.Forms.Button
    Friend WithEvents ButtonLedOff3 As System.Windows.Forms.Button
    Friend WithEvents ButtonLedOff4 As System.Windows.Forms.Button

End Class
