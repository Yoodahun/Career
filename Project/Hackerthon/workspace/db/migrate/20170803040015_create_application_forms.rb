class CreateApplicationForms < ActiveRecord::Migration
  def change
    
    create_table :application_forms do |t|
      t.integer :user_id
      t.text :area
      t.text :hotel
      t.text :email
      t.integer :peopleNumber
      t.text :representiveName
      t.string :check_in
      t.string :check_out
      t.string :sex
      t.boolean :match
      t.integer :user_id_head
      t.integer :user_id_tail
      t.boolean :match_head
      t.boolean :match_tail
      
      t.timestamps null: false
    end
  end
end
