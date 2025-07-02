//package bloodBank;
import java.util.Scanner;
import java.util.Formatter;

public class BloodBank extends Donor {
	Scanner sc = new Scanner(System.in);
	Scanner scan = new Scanner(System.in);
	int donor_no, HCV, HbSag;
	float A = 1050, a = 1050, B = 1050, b = 1050, O = 1050, o = 1050, AB = 1050, ab = 1050; // Quantity of Blood																						// according to the bloo																						// groups
	float hb;
	float total_blood;
	String hosp_blood;
		void eligibility() {
			// Checking if the donor is eligible to donate blood
			int flag = 0;
			System.out.println("Enter your name ");
			name = sc.nextLine();
			System.out.println("Enter blood test reports as asked : ");
			System.out.println("Enter your heamoglobin levels : ");
			hb = scan.nextFloat();
			if (hb < 12.5) {
				System.out.println("Not eligible to donate blood due to low heamoglobin!");
				flag = 1;

			}
			
			if (flag == 0) {
				
				
					System.out.println("Have you Donated Blood in the past three months ?\nEnter 1 if not and 2 if yes.");
					int blood_donation = scan.nextInt();
					if (blood_donation == 2) {
						System.out.println("You cannot donate blood within 3 months of your previous blood donation");
						flag = 1;
					}
				
			}

			if (flag == 1) {
				System.out.println("You are not eligible to donate blood");
			} else {
				System.out.println("You can proceed to next the counter");
				create(); // creates new node for the donor
				// display(); // displays certificate of donation
			}
		}

		void bloodQuantity1() {
			// method to calculate the blood quantity according to donated blood
//			A = 1050; // Quantity of A+
//			a = 1050; // Quantity of A-
//			B = 1050; // Quantity of B+
//			b = 1050; // Quantity of B-
//			O = 1050; // Quantity of O+
//			o = 1050; // Quantity of O-
//			AB = 1050; // Quantity of AB+
//			ab = 1050; // Quantity of AB-
			ptr = head;
			while (ptr != null) {
				switch (ptr.blood_grp) {
				case "A+":
					A = A + 350;
					break;
				case "A-":
					a = a + 350;
					break;
				case "B+":
					B = B + 350;
					break;
				case "B-":
					b = b + 350;
					break;
				case "O+":
					O = O + 350;
					break;
				case "O-":
					o = o + 350;
					break;
				case "AB+":
					AB = AB + 350;
					break;
				case "AB-":
					ab = ab + 350;
					break;
				}
				ptr = ptr.next;
			}
			total_blood = A + a + B + b + O + o + AB + ab;
		}

		void bloodQuantity2() {
			// method to give the blood to hospitals and simultaneously calculate the
			// quantity
			ptr = head;
			int c;
			do {
				System.out.println("1.Blood");
				System.out.println("Enter your choice : ");
				int choice = scan.nextInt();
				switch (choice) {
				// calling methods based on user's choice
				case 1: {
					blood();
					break;
				}
			    default: {
					System.out.println("Invalid choice");
				}
				
				}
				System.out.println("Enter 1 if you want to continue placing order.");
				c = scan.nextInt();
			} while (c == 1);
			Formatter fmt = new Formatter();
			System.out.println("The Quantity of Blood Available according to the blood groups is:");
			System.out.printf("%15s %15s %15s %15s %15s %15s %15s %15s\n", "A+", "A-", "B+", "B-", "O+", "O-", "AB+",
					"AB-");
			fmt.format("%15s %15s %15s %15s %15s %15s %15s %15s\n", A + " ml", a + " ml", B + " ml", b + " ml", O + " ml",
					o + " ml", AB + " ml", ab + "ml");
			System.out.println(fmt);
		}

		

		
		void blood() {
			System.out.println("Enter the Blood Group required");
			String hosp_blood = sc.nextLine();
			switch (hosp_blood) {
			case "A+":
				System.out.println("Enter the quantity required in ml");
				int Aq = scan.nextInt(); // Requirement of blood
				if (Aq <= A) {
					A = A - Aq; // Giving hospital required blood and updating blood quantity in system
				} else {
					// Giving hospital as much quantity we have, if we can't fulfill the requirement
					System.out.println("Sufficient blood not available");
					System.out.println("We have " + A
							+ " ml amount of blood available. We can Check if any quantity of O- blood group is availabe as O- is a Universal Donor group.");
					if (A != 0) {
						System.out.println("Press 1 if you want to proceed");
						int flag = scan.nextInt();
						if (flag == 1) {
							float n = Aq - A;
							A = 0;
							if (o != 0) {
								// To check how much O- blood is needed to fulfill the requirement
								System.out.println(n);
								// O- is universal donor so we try to fulfill requirement by O- blood
								System.out.println("We have " + o + "ml quantity of O- which is universal donor");
								System.out.println(n + "ml of O- blood is included in order");
								if (n >= o) {
									// if requirement is more than O- blood quantity then give all the O- blood
									System.out.println(o + "ml of O- blood is included in order");
									o = 0;
								}
								if (n < o) {
									// If requirement is less than O- blood then its fulfilled by combination of O-
									// blood and A+ blood
									System.out.println("Requirement is fulfilled. ");// + A + "ml A+ blood and" + n + "ml
																						// O- blood");
									o = o - n;
								}
								System.out.println("Order placed !");
							} else {
								System.out.println("Let us know about any further requirement !");
							}
						}
					}
				}
				break;
			case "A-":
				System.out.println("Enter the quantity required in ml");
				int aq = scan.nextInt(); // Requirement of blood
				if (aq <= a) {
					a = a - aq; // Giving hospital required blood and updating blood quantity in system
				} else {
					// Giving hospital as much quantity we have, if we can't fulfill the requirement
					System.out.println("Sufficient blood not available");
					System.out.println("We have " + a
							+ " ml amount of blood available. We can Check if any quantity of O- blood group is availabe as O- is a Universal Donor group.");
					if (a != 0) {
						System.out.println("Press 1 if you want to proceed");
						flag = scan.nextInt();
						if (flag == 1) {
							float n = aq - a;
							a = 0;
							if (o != 0) {
								// To check how much O- blood is needed to fulfill the requirement

								// O- is universal donor so we try to fulfill requirement by O- blood
								System.out.println("We have " + o + " ml quantity of O- which is universal donor");
								System.out.println(n + "ml of O- blood is included in order");
								if (n >= o) {
									// if requirement is more than O- blood quantity then give all the O- blood
									System.out.println(o + "ml of O- blood is included in order");
									o = 0;
								} else {
									// If requirement is less than O- blood then its fulfilled by combination of O-
									// blood and A- blood
									System.out.println("Requirement is fulfilled.");
									o = o - n;
								}
							}
						}
					} else {
						System.out.println("Let us know about any further requirement !");
					}
				}
				break;
			case "B+":
				System.out.println("Enter the quantity required in ml");
				int Bq = scan.nextInt(); // Requirement of blood
				if (Bq <= B) {
					B = B - Bq; // Giving hospital required blood and updating blood quantity in system
				} else {
					// Giving hospital as much quantity we have, if we can't fulfill the requirement
					System.out.println("Sufficient blood not available");
					System.out.println("We have " + B
							+ " ml amount of blood available. We can Check if any quantity of O- blood group is availabe as O- is a Universal Donor group.");
					if (B != 0) {
						System.out.println("Press 1 if you want to proceed ");
						int flag = scan.nextInt();
						if (flag == 1) {
							float n = Bq - B;
							B = 0;
							if (o != 0) {
								// To check how much O- blood is needed to fulfill the requirement

								// O- is universal donor so we try to fulfill requirement by O- blood
								System.out.println("We have " + o + "ml quantity of O- which is universal donor");
								System.out.println(n + "ml of O- blood is included in order");
								if (n >= o) {
									// if requirement is more than O- blood quantity then give all the O- blood
									System.out.println(o + "ml of O- blood is included in order");
									o = 0;
								} else {
									// If requirement is less than O- blood then its fulfilled by combination of O-
									// blood and B+ blood
									System.out.println("Requirement is fulfilled.");
									o = o - n;
								}
							}
						}
					} else {
						System.out.println("Let us know about any further requirement !");
					}
				}
				break;
			case "B-":
				System.out.println("Enter the quantity required in ml");
				int bq = scan.nextInt(); // Requirement of blood
				if (bq <= b) {
					b = b - bq; // Giving hospital required blood and updating blood quantity in system
				} else {
					// Giving hospital as much quantity we have, if we can't fulfill the requirement
					System.out.println("Sufficient blood not available");
					System.out.println("We have " + b
							+ " ml amount of blood available. We can Check if any quantity of O- blood group is availabe as O- is a Universal Donor group.");
					if (b != 0) {
						System.out.println("Press 1 if you want to proceed");
						int flag = scan.nextInt();
						if (flag == 1) {
							float n = bq - b;
							b = 0;
							if (o != 0) {
								// To check how much O- blood is needed to fulfill the requirement

								// O- is universal donor so we try to fulfill requirement by O- blood
								System.out.println("We have " + o + "ml quantity of O- which is universal donor");
								System.out.println(n + "ml of O- blood is included in order");
								if (n >= o) {
									// if requirement is more than O- blood quantity then give all the O- blood
									System.out.println(o + "ml of O- blood is included in order");
									o = 0;
								} else {
									// If requirement is less than O- blood then its fulfilled by combination of O-
									// blood and B- blood
									System.out.println("Requirement is fulfilled.");
									o = o - n;
								}
							}

						}
					} else {
						System.out.println("Let us know about any further requirement !");
					}
				}
				break;
			case "O+":
				System.out.println("Enter the quantity required in ml");
				int Oq = scan.nextInt(); // Requirement of blood
				if (Oq <= O) {
					O = O - Oq; // Giving hospital required blood and updating blood quantity in system
				} else {
					// Giving hospital as much quantity we have, if we can't fulfill the requirement
					System.out.println("Sufficient blood not available");
					System.out.println("We have " + O
							+ " amount of blood available. We can Check if any quantity of O- blood group is availabe as O- is a Universal Donor group.");
					if (O != 0) {
						System.out.println("Press 1 if you want to proceed");
						int flag = scan.nextInt();
						if (flag == 1) {
							float n = Oq - O;
							O = 0;
							if (o != 0) {
								// To check how much O- blood is needed to fulfill the requirement

								// O- is universal donor so we try to fulfill requirement by O- blood
								System.out.println("We have " + o + " quantity of O- which is universal donor");
								System.out.println(n + "ml of O- blood is included in order");
								if (n >= o) {
									// if requirement is more than O- blood quantity then give all the O- blood
									System.out.println(o + "ml of O- blood is included in order");
									o = 0;
								} else {
									// If requirement is less than O- blood then its fulfilled by combination of O-
									// blood and O+ blood
									System.out.println("Requirement is fulfilled.");
									o = o - n;
								}
							}
						}
					} else {
						System.out.println("Let us know about any further requirement !");
					}
				}
				break;
			case "O-":
				System.out.println("Enter the quantity required in ml");
				int oq = scan.nextInt(); // Requirement of blood
				if (oq < o) {
					o = o - oq; // Giving hospital required blood and updating blood quantity in system
				} else {
					// Giving hospital as much quantity we have, if we can't fulfill the requirement
					System.out.println("Sufficient blood not available");
					System.out.println("We have" + o + " amount of blood available.");
					if (o != 0) {
						System.out.println("Press 1 if you want to proceed with this order.");
						int flag = scan.nextInt();
						if (flag == 1) {
							o = 0;
						}
					} else {
						System.out.println("Let us know about any further requirement !");
					}
				}
				break;
			case "AB+":
				System.out.println("Enter the quantity required in ml");
				int ABq = scan.nextInt(); // Requirement of blood
				if (ABq <= AB) {
					AB = AB - ABq; // Giving hospital required blood and updating blood quantity in system
				} else {
					// Giving hospital as much quantity we have, if we can't fulfill the requirement
					System.out.println("Sufficient blood not available");
					System.out.println("We have " + AB
							+ " ml amount of blood available. We can Check if any quantity of O- blood group is availabe as O- is a Universal Donor group.");
					if (AB != 0) {
						System.out.println("Press 1 if you want to proceed");
						int flag = scan.nextInt();
						if (flag == 1) {
							float n = ABq - AB;
							AB = 0;
							if (o != 0) {
								// To check how much O- blood is needed to fulfill the requirement

								// O- is universal donor so we try to fulfill requirement by O- blood
								System.out.println("We have " + o + "ml quantity of O- which is universal donor");
								System.out.println(n + "ml of O- blood is included in order");
								if (n >= o) {
									// if requirement is more than O- blood quantity then give all the O- blood
									System.out.println(o + "ml of O- blood is included in order");
									o = 0;
								} else {
									// If requirement is less than O- blood then its fulfilled by combination of O-
									// blood and AB+ blood
									System.out.println("Requirement is fulfilled.");
									o = o - n;
								}
							}
						}
					} else {
						System.out.println("Let us know about any further requirement !");
					}
				}
				break;
			case "AB-":
				System.out.println("Enter the quantity required in ml");
				int abq = scan.nextInt(); // Requirement of blood
				if (abq <= ab) {
					ab = ab - abq; // Giving hospital required blood and updating blood quantity in system
				} else {
					// Giving hospital as much quantity we have, if we can't fulfill the requirement
					System.out.println("Sufficient blood not available");
					System.out.println("We have" + ab
							+ " amount of blood available. We can Check if any quantity of O- blood group is availabe as O- is a Universal Donor group.");
					if (ab != 0) {
						System.out.println("Press 1 if you want to proceed");
						int flag = scan.nextInt();
						if (flag == 1) {
							float n = abq - ab;
							ab = 0;
							if (o != 0) {
								// To check how much O- blood is needed to fulfill the requirement

								// O- is universal donor so we try to fulfill requirement by O- blood
								System.out.println("We have " + o + " quantity of O- which is universal donor");
								System.out.println(n + "ml of O- blood is included in order");
								if (n >= o) {
									// if requirement is more than O- blood quantity then give all the O- blood
									System.out.println(o + "ml of O- blood is included in order");
									o = 0;
								} else {
									// If requirement is less than O- blood then its fulfilled by combination of O-
									// blood and AB- blood
									System.out.println("Requirement is fulfilled.");
									o = o - n;
								}
							}
						}
					} else {
						System.out.println("Let us know about any further requirement !");
					}
				}
				break;
			}
			total_blood = A + a + B + b + O + o + AB + ab; // updating the total blood amount
		}

		void display() {
			// Donor certificate display
			ptr = head;
			System.out.println("Please Enter the following Details to get your certificate:");
			System.out.println("Enter your name");
			String donor_name = sc.nextLine();
			System.out.println("Enter your number");
			donor_no = scan.nextInt();
			int flag = 0;
			while (ptr != null) {
				if (ptr.name.equals(donor_name) && ptr.count == donor_no) {
					System.out.println("-----------------CERTIFICATE OF BLOOD DONATION--------------------");
					System.out.println("                    THANK YOU " + ptr.name + "FOR YOUR BLOOD DONATION");
					System.out.println("                    Donor Name:" + ptr.name);
					System.out.println("                    Donor Age:" + ptr.age);
					System.out.println("                    Donor Blood Group:" + ptr.blood_grp);
					System.out.println("           THANKYOU FOR DONATING BLOOD IN AMRITA BLOOD BANK    ");
					System.out.println("                    EVERY BLOOD DONOR IS A LIFE SAVER  ");
					flag = 1;
				}
				ptr = ptr.next;
			}
			if (flag == 0) {
				System.out.println("Data of Donation not Found.");
			}
		}

		void display1() {
			System.out.println("Number of Donors that came in Today: " + count);
			System.out.println("The Quantity of Blood in the Blood Bank:");
			Formatter fmt = new Formatter();
			System.out.println("The Quantity of Blood Available according to the blood groups is:");
			System.out.printf("%15s %15s %15s %15s %15s %15s %15s %15s\n", "A+", "A-", "B+", "B-", "O+", "O-", "AB+",
					"AB-");
			fmt.format("%15s %15s %15s %15s %15s %15s %15s %15s\n", A + " ml", a + " ml", B + " ml", b + " ml", O + " ml",
					o + " ml", AB + " ml", ab + "ml");
			System.out.println(fmt);
		}

		void display2() {
			System.out.println("List of Donors who Donated Blood Today:");
			ptr = head;
			while (ptr != null) {
				System.out.println(ptr.name);
				ptr = ptr.next;
			}
		}

		

	}
